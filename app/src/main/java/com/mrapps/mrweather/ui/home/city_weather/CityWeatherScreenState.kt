package com.mrapps.mrweather.ui.home.city_weather

import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.domain.model.weather_condition.CurrentConditions

data class CityWeatherScreenState(
    val city: City? = null,
    val conditions: CurrentConditions? = null,
    val isCityLoading: Boolean = false,
    val isConditionsLoading: Boolean = false,
    val error: Throwable? = null,
)