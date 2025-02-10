package com.mrapps.mrweather.data.remote.dto.unit

import com.google.gson.annotations.SerializedName

data class UnitsDto(
    @SerializedName("Imperial") val imperial: UnitDoubleDto,
    @SerializedName("Metric") val metric: UnitDoubleDto
)