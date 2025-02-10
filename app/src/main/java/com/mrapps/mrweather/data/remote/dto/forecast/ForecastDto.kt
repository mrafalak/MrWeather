package com.mrapps.mrweather.data.remote.dto.forecast

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("DailyForecasts") val dailyForecasts: List<DailyForecastDto>,
)