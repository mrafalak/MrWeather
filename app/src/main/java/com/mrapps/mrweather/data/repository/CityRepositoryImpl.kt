package com.mrapps.mrweather.data.repository

import com.mrapps.mrweather.data.local.dao.CityDao
import com.mrapps.mrweather.data.local.dao.SearchHistoryDao
import com.mrapps.mrweather.data.local.entity.search_history.SearchHistoryEntity
import com.mrapps.mrweather.data.local.mappers.toCity
import com.mrapps.mrweather.data.local.mappers.toCityEntity
import com.mrapps.mrweather.data.local.mappers.toSearchHistoryWithCity
import com.mrapps.mrweather.data.local.util.safeDatabaseOperation
import com.mrapps.mrweather.data.remote.WeatherApi
import com.mrapps.mrweather.data.remote.dto.location.toCityList
import com.mrapps.mrweather.data.remote.util.safeApiCall
import com.mrapps.mrweather.domain.CityRepository
import com.mrapps.mrweather.domain.model.SearchHistoryWithCity
import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.domain.model.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime

class CityRepositoryImpl(
    private val api: WeatherApi,
    private val cityDao: CityDao,
    private val searchHistoryDao: SearchHistoryDao
) : CityRepository {

    override suspend fun searchCities(query: String): Flow<Result<List<City>>> = flow {
        val result = safeApiCall { api.searchCities(query).toCityList() }
        emit(result)
    }

    override suspend fun saveCityAndSearchHistory(city: City): Flow<Result<Unit>> = flow {
        val cityResult = safeDatabaseOperation {
            cityDao.getCityById(city.id)
        }

        val result = when (cityResult) {
            is Result.Success -> {
                if (cityResult.data != null) {
                    saveOrUpdateSearch(city.id)
                } else {
                    saveNewCity(city)
                }
            }

            is Result.Exception -> cityResult
        }
        emit(result)
    }

    override suspend fun getSearchHistory(): Flow<List<SearchHistoryWithCity>> {
        return searchHistoryDao.getAllSearchHistoryWithCities().map { searchHistoryWithCitiesData ->
            searchHistoryWithCitiesData.map { it.toSearchHistoryWithCity() }
        }
    }

    override suspend fun getCityById(cityId: String): Flow<Result<City>> = flow {
        val cityResult = safeDatabaseOperation {
            cityDao.getCityById(cityId)?.toCity()
        }

        when (cityResult) {
            is Result.Success -> {
                val city = cityResult.data
                if (city == null) {
                    emit(Result.Exception(Exception("Unable to retrieve city data")))
                } else {
                    emit(Result.Success(city))
                }
            }

            is Result.Exception -> emit(cityResult)
        }
    }

    private suspend fun saveOrUpdateSearch(cityId: String): Result<Unit> {
        val searchHistoryResult = safeDatabaseOperation {
            searchHistoryDao.getSearchHistoryById(cityId)
        }

        return when (searchHistoryResult) {
            is Result.Success -> {
                if (searchHistoryResult.data != null) {
                    updateSearchHistoryTime(cityId)
                } else {
                    saveNewSearchHistory(cityId)
                }
            }

            is Result.Exception -> searchHistoryResult
        }
    }

    private suspend fun saveNewCity(city: City): Result<Unit> {
        val cityEntity = city.toCityEntity()
        val searchHistoryEntity = SearchHistoryEntity(
            cityId = city.id,
            searchTime = LocalDateTime.now()
        )

        return safeDatabaseOperation {
            cityDao.insertCityAndSearchHistory(cityEntity, searchHistoryEntity)
        }
    }

    private suspend fun saveNewSearchHistory(cityId: String): Result<Unit> {
        return safeDatabaseOperation {
            searchHistoryDao.insertSearchHistory(
                SearchHistoryEntity(
                    cityId = cityId,
                    searchTime = LocalDateTime.now()
                )
            )
        }
    }

    private suspend fun updateSearchHistoryTime(cityId: String): Result<Unit> {
        return safeDatabaseOperation {
            searchHistoryDao.updateSearchTime(cityId, LocalDateTime.now())
        }
    }
}