package com.mrapps.mrweather.ui.home.city_weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrapps.mrweather.domain.CityRepository
import com.mrapps.mrweather.domain.WeatherRepository
import com.mrapps.mrweather.domain.model.util.Result
import com.mrapps.mrweather.ui.home.city_weather.CityWeatherScreenAction.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CityWeatherViewModel(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CityWeatherScreenState())
    val state: StateFlow<CityWeatherScreenState> = _state

    fun onAction(action: CityWeatherScreenAction) {
        when (action) {
            is FetchCityData -> fetchCityData(action.cityId)
            is FetchCurrentConditions -> fetchCurrentConditions(action.cityId)
            is FetchForecast -> fetchForecast(action.cityId)
            ClearError -> clearError()
            else -> Unit
        }
    }

    private fun fetchCityData(cityId: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isCityLoading = true, cityId = cityId)
            cityRepository.getCityById(cityId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = state.value.copy(
                            city = result.data,
                            isCityLoading = false
                        )
                    }

                    is Result.Exception -> {
                        _state.value = state.value.copy(
                            isCityLoading = false,
                            error = result.error,
                        )
                    }
                }
            }
        }
    }

    private fun fetchCurrentConditions(cityId: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isConditionsLoading = true)
            weatherRepository.fetchCurrentConditions(cityId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = state.value.copy(
                            weatherConditions = result.data,
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

    private fun fetchForecast(cityId: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isForecastLoading = true)
            weatherRepository.fetchFiveDaysForecast(cityId, state.value.unitSystemType)
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _state.value = state.value.copy(
                                forecast = result.data,
                                isForecastLoading = false
                            )
                        }

                        is Result.Exception -> {
                            _state.value = state.value.copy(
                                isForecastLoading = false,
                                error = result.error,
                            )
                        }
                    }
                }
        }
    }

    private fun clearError() {
        _state.value = state.value.copy(error = null)
    }
}