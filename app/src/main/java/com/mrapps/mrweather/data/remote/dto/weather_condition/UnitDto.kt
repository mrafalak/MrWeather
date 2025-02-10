package com.mrapps.mrweather.data.remote.dto.weather_condition

import com.google.gson.annotations.SerializedName

data class UnitDto(
    @SerializedName("Imperial") val imperial: ImperialDto,
    @SerializedName("Metric") val metric: MetricDto
)