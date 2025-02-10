package com.mrapps.mrweather.domain

import com.mrapps.mrweather.domain.model.forecast.DailyForecast
import com.mrapps.mrweather.domain.model.units.UnitSystemType
import com.mrapps.mrweather.domain.model.util.Result
import com.mrapps.mrweather.domain.model.weather_condition.WeatherConditions
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun fetchCurrentConditions(cityId: String): Flow<Result<WeatherConditions>>
    suspend fun fetchFiveDaysForecast(
        cityId: String,
        unitSystemType: UnitSystemType
    ): Flow<Result<List<DailyForecast>>>
}