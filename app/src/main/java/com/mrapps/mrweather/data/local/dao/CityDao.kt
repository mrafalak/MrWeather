package com.mrapps.mrweather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.mrapps.mrweather.data.local.entity.city.CityEntity
import com.mrapps.mrweather.data.local.entity.search_history.SearchHistoryEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM cities WHERE city_id = :cityId")
    suspend fun getCityById(cityId: String): CityEntity?

    @Insert
    suspend fun insertCity(city: CityEntity)

    @Insert
    suspend fun insertSearchHistory(searchHistoryEntity: SearchHistoryEntity)

    @Transaction
    suspend fun insertCityAndSearchHistory(
        city: CityEntity,
        searchHistoryEntity: SearchHistoryEntity
    ) {
        insertCity(city)
        insertSearchHistory(searchHistoryEntity)
    }
}