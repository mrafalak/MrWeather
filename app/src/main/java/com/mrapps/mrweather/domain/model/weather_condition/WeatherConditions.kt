package com.mrapps.mrweather.domain.model.weather_condition

import com.mrapps.mrweather.domain.model.units.UnitType
import java.time.LocalDateTime

data class WeatherConditions(
    val weatherText: String,
    val weatherIcon: Int,
    val temperature: UnitType.Temperature,
    val realFeelTemperature: UnitType.Temperature,
    val realFeelTemperatureShade: UnitType.Temperature,
    val relativeHumidity: Int,
    val hasPrecipitation: Boolean,
    val precipitationType: PrecipitationType,
    val wind: Wind,
    val cloudCover: Int,
    val pressure: UnitType.Pressure,
    val pressureTendency: PressureTendency,
    val localObservationDateTime: LocalDateTime,
    val epochTime: Int
)