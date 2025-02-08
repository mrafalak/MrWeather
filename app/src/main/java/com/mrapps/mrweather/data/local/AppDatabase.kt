package com.mrapps.mrweather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mrapps.mrweather.data.local.dao.CityDao
import com.mrapps.mrweather.data.local.dao.SearchHistoryDao
import com.mrapps.mrweather.data.local.entity.city.CityEntity
import com.mrapps.mrweather.data.local.entity.search_history.SearchHistoryEntity

@Database(
    entities = [
        CityEntity::class,
        SearchHistoryEntity::class
    ],
    version = LocalDataConfig.DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun searchHistoryDao(): SearchHistoryDao
}