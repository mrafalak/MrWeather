package com.mrapps.mrweather.domain.model.weather_condition

import com.mrapps.mrweather.domain.model.units.Unit

data class WeatherConditions(
    val weatherText: String,
    val weatherIcon: Int,
    val temperature: Unit,
    val realFeelTemperature: Unit,
    val realFeelTemperatureShade: Unit,
    val hasPrecipitation: Boolean,
    val wind: Wind,
    val cloudCover: Int,
    val pressure: Unit,
    val pressureTendency: PressureTendency,
    val localObservationDateTime: String,
    val epochTime: Int
)