package com.mrapps.mrweather.domain

import com.mrapps.mrweather.domain.model.SearchHistoryWithCity
import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.domain.model.util.Result
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    suspend fun searchCities(query: String): Flow<Result<List<City>>>
    suspend fun saveCityAndSearchHistory(city: City): Flow<Result<Unit>>
    suspend fun getSearchHistory(): Flow<List<SearchHistoryWithCity>>
}