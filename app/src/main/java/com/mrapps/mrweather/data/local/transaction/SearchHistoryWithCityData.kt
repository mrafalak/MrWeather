package com.mrapps.mrweather.data.local.transaction

import androidx.room.Embedded
import androidx.room.Relation
import com.mrapps.mrweather.data.local.entity.city.CityEntity
import com.mrapps.mrweather.data.local.entity.search_history.SearchHistoryEntity

data class SearchHistoryWithCityData(
    @Embedded val searchHistory: SearchHistoryEntity,
    @Relation(
        parentColumn = "city_id",
        entityColumn = "city_id"
    )
    val city: CityEntity
)