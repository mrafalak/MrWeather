package com.mrapps.mrweather.data.remote.dto.weather_condition

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.weather_condition.Wind

data class WindDto(
    @SerializedName("Direction") val direction: DirectionDto,
    @SerializedName("Speed") val speed: UnitDto
) {
    fun toWind(): Wind = Wind(
        direction = this.direction.toDirection(),
        speed = this.speed.toUnit()
    )
}