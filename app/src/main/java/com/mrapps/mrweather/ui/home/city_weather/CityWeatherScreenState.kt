package com.mrapps.mrweather.ui.home.city_weather

import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.domain.model.weather_condition.WeatherConditions

data class CityWeatherScreenState(
    val cityId: String? = null,
    val city: City? = null,
    val weatherConditions: WeatherConditions? = null,
    val isCityLoading: Boolean = false,
    val isConditionsLoading: Boolean = false,
    val error: Throwable? = null,
)