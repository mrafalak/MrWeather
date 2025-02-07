package com.mrapps.mrweather.data.repository

import com.mrapps.mrweather.data.remote.WeatherApi
import com.mrapps.mrweather.data.remote.safeApiCall
import com.mrapps.mrweather.domain.WeatherRepository
import com.mrapps.mrweather.domain.model.util.Result
import com.mrapps.mrweather.domain.model.weather_condition.CurrentConditions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun fetchCurrentConditions(
        cityId: String
    ): Flow<Result<CurrentConditions>> = flow {
        val result = safeApiCall {
            api.fetchCurrentConditions(locationKey = cityId)
                .first()
                .toCurrentConditions()
        }
        emit(result)
    }
}