package com.mrapps.mrweather.data.remote.dto.weather_condition

import com.google.gson.annotations.SerializedName

data class PressureTendencyDto(
    @SerializedName("Code") val code: String,
)