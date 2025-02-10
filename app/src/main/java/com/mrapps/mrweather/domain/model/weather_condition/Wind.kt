package com.mrapps.mrweather.domain.model.weather_condition

import com.mrapps.mrweather.domain.model.units.UnitType

data class Wind(
    val direction: Direction,
    val speed: UnitType.Speed
)