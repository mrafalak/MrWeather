package com.mrapps.mrweather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.mrapps.mrweather.data.local.entity.search_history.SearchHistoryEntity
import com.mrapps.mrweather.data.local.transaction.SearchHistoryWithCityData
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface SearchHistoryDao {

    @Insert
    suspend fun insertSearchHistory(searchHistoryEntity: SearchHistoryEntity)

    @Query("SELECT * FROM search_history WHERE city_id = :cityId")
    suspend fun getSearchHistoryById(cityId: String): SearchHistoryEntity?

    @Query("UPDATE search_history SET search_time = :newSearchTime WHERE city_id = :cityId")
    suspend fun updateSearchTime(cityId: String, newSearchTime: LocalDateTime)

    @Transaction
    @Query("SELECT * FROM search_history ORDER BY search_time DESC")
    fun getAllSearchHistoryWithCities(): Flow<List<SearchHistoryWithCityData>>
}