package com.mrapps.mrweather.ui.home.city_weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrapps.mrweather.domain.WeatherRepository
import com.mrapps.mrweather.domain.model.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CityWeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _state = MutableStateFlow(CityWeatherScreenState())
    val state: StateFlow<CityWeatherScreenState> = _state

    fun onAction(action: CityWeatherScreenAction) {
        when (action) {
            is CityWeatherScreenAction.FetchCurrentConditions -> fetchCurrentConditions(action.cityId)
            else -> Unit
        }
    }

    private fun fetchCurrentConditions(cityId: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isConditionsLoading = true)
            weatherRepository.fetchCurrentConditions(cityId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = state.value.copy(
                            conditions = result.data,
                            isConditionsLoading = false
                        )
                    }

                    is Result.Exception -> {
                        _state.value = state.value.copy(
                            isConditionsLoading = false,
                            error = result.error,
                        )
                    }
                }
            }
        }
    }
}