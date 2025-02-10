package com.mrapps.mrweather.domain.model.forecast

import com.mrapps.mrweather.domain.model.units.UnitType

data class TemperaturesRange(
    val maximum: UnitType.Temperature,
    val minimum: UnitType.Temperature
)