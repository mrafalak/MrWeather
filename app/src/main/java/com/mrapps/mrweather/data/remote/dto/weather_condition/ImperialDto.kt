package com.mrapps.mrweather.data.remote.dto.weather_condition

import com.google.gson.annotations.SerializedName

data class ImperialDto(
    @SerializedName("Unit") val unit: String,
    @SerializedName("UnitType") val unitType: Int,
    @SerializedName("Value") val value: Double
)