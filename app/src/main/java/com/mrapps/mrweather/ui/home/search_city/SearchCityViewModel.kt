package com.mrapps.mrweather.ui.home.search_city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrapps.mrweather.domain.CityRepository
import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.domain.model.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchCityViewModel(private val cityRepository: CityRepository) : ViewModel() {

    private val _state = MutableStateFlow(SearchCityScreenState())
    val state: StateFlow<SearchCityScreenState> = _state
        .onStart {
            getSearchHistory()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            SearchCityScreenState()
        )

    private val _effect = Channel<SearchScreenEffect>()
    val effect = _effect.receiveAsFlow()

    fun onAction(action: SearchCityScreenAction) {
        when (action) {
            is SearchCityScreenAction.QueryChanged -> onQueryChanged(action.query)
            SearchCityScreenAction.SearchClicked -> searchCity(state.value.query.trim())
            SearchCityScreenAction.ClearError -> clearError()
            is SearchCityScreenAction.CityClicked -> saveCityAndSearchHistory(action.city)
        }
    }

    private fun getSearchHistory() {
        viewModelScope.launch {
            cityRepository.getSearchHistory().collect { result ->
                _state.value = state.value.copy(searchHistoryWithCities = result)
            }
        }
    }

    private fun searchCity(query: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            cityRepository.searchCities(query).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = state.value.copy(
                            cities = result.data,
                            isLoading = false
                        )
                    }

                    is Result.Exception -> {
                        _state.value = state.value.copy(
                            cities = emptyList(),
                            isLoading = false,
                            error = result.error,
                        )
                    }
                }
            }
        }
    }

    private fun saveCityAndSearchHistory(city: City) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true, isCitySaving = true)
            cityRepository.saveCityAndSearchHistory(city).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            isCitySaving = false
                        )
                        _effect.send(SearchScreenEffect.NavigateToCityWeather(cityId = city.id))
                    }

                    is Result.Exception -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            isCitySaving = false,
                            error = result.error,
                        )
                    }
                }
            }
        }
    }

    private fun onQueryChanged(newQuery: String) {
        _state.value = state.value.copy(
            query = newQuery,
            cities = emptyList()
        )
    }

    private fun clearError() {
        _state.value = state.value.copy(error = null)
    }
}

sealed interface SearchScreenEffect {
    data class NavigateToCityWeather(val cityId: String) : SearchScreenEffect
}