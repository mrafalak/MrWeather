package com.mrapps.mrweather.data.remote.dto.unit

import com.google.gson.annotations.SerializedName

data class UnitDoubleDto(
    @SerializedName("Unit") val unit: String,
    @SerializedName("UnitType") val unitType: Int,
    @SerializedName("Value") val value: Double
)