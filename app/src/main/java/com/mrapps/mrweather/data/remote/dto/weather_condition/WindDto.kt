package com.mrapps.mrweather.data.remote.dto.weather_condition

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.data.remote.dto.unit.UnitDoubleDto
import com.mrapps.mrweather.domain.model.units.UnitType
import com.mrapps.mrweather.domain.model.weather_condition.Wind
import com.mrapps.mrweather.ui.util.roundToSingleDecimal

data class WindDto(
    @SerializedName("Direction") val direction: DirectionDto,
    @SerializedName("Speed") val speed: UnitDoubleDto
) {
    fun toWind(metric: Boolean): Wind? {
        val value = this.speed.value
        return if (value == null) {
            null
        } else {
            val metricValue = if (metric) value else value / 0.621371
            val imperialValue = if (metric) value * 0.621371 else value

            Wind(
                direction = this.direction.toDirection(),
                speed = UnitType.Speed(
                    kmh = roundToSingleDecimal(metricValue),
                    mph = roundToSingleDecimal(imperialValue)
                )
            )
        }
    }
}