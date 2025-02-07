package com.mrapps.mrweather.ui.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrapps.mrweather.domain.CityRepository
import com.mrapps.mrweather.domain.model.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val cityRepository: CityRepository) : ViewModel() {

    private val _state = MutableStateFlow(SearchScreenState())
    val state: StateFlow<SearchScreenState> = _state

    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.QueryChanged -> onQueryChanged(action.query)
            SearchAction.SearchClicked -> searchCity(state.value.query.trim())
            SearchAction.ClearError -> clearError()
            else -> Unit
        }
    }

    private fun searchCity(query: String) {
        viewModelScope.launch {
            startLoading()
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

    private fun onQueryChanged(newQuery: String) {
        _state.value = state.value.copy(query = newQuery)
    }

    private fun clearError() {
        _state.value = state.value.copy(error = null)
    }

    private fun startLoading() {
        _state.value = state.value.copy(isLoading = true)
    }
}