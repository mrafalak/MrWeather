package com.mrapps.mrweather.data.repository

import com.mrapps.mrweather.data.remote.WeatherApi
import com.mrapps.mrweather.data.remote.util.safeApiCall
import com.mrapps.mrweather.domain.WeatherRepository
import com.mrapps.mrweather.domain.model.forecast.DailyForecast
import com.mrapps.mrweather.domain.model.units.UnitSystemType
import com.mrapps.mrweather.domain.model.util.Result
import com.mrapps.mrweather.domain.model.weather_condition.WeatherConditions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun fetchCurrentConditions(
        cityId: String,
    ): Flow<Result<WeatherConditions>> = flow {
        val result = safeApiCall {
            api.fetchCurrentConditions(locationKey = cityId)
                .first()
                .toWeatherConditions()
        }
        emit(result)
    }

    override suspend fun fetchFiveDaysForecast(
        cityId: String,
        unitSystemType: UnitSystemType
    ): Flow<Result<List<DailyForecast>>> = flow {
        val metric = when (unitSystemType) {
            UnitSystemType.METRIC -> true
            UnitSystemType.IMPERIAL -> false
        }
        val result = safeApiCall {
            api.fetchFiveDaysForecast(
                locationKey = cityId,
                metric = metric
            ).dailyForecasts.map {
                it.toDailyForecast(metric)
            }
        }
        emit(result)
    }
}