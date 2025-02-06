package com.mrapps.mrweather.data.repository

import com.mrapps.mrweather.data.remote.WeatherApi
import com.mrapps.mrweather.data.remote.dto.toCityList
import com.mrapps.mrweather.data.remote.safeApiCall
import com.mrapps.mrweather.domain.CityRepository
import com.mrapps.mrweather.domain.model.City
import com.mrapps.mrweather.domain.model.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CityRepositoryImpl(private val api: WeatherApi) : CityRepository {

    override suspend fun searchCities(query: String): Flow<Result<List<City>>> = flow {
        val result = safeApiCall { api.searchCities(query).toCityList() }
        emit(result)
    }
}