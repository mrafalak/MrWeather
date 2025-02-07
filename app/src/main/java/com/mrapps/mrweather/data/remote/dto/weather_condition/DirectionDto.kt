package com.mrapps.mrweather.data.remote.dto.weather_condition

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.weather_condition.Direction

data class DirectionDto(
    @SerializedName("Degrees") val degrees: Int,
    @SerializedName("English") val english: String,
    @SerializedName("Localized") val localized: String
) {
    fun toDirection(): Direction = Direction(
        degrees = this.degrees,
        english = this.english,
        localized = this.localized
    )
}