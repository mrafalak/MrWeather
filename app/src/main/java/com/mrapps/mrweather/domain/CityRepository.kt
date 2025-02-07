package com.mrapps.mrweather.domain

import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.domain.model.util.Result
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    suspend fun searchCities(query: String): Flow<Result<List<City>>>
}