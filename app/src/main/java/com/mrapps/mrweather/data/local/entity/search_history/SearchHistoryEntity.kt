package com.mrapps.mrweather.data.local.entity.search_history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.mrapps.mrweather.data.local.entity.city.CityEntity
import java.time.LocalDateTime

@Entity(
    tableName = "search_history",
    foreignKeys = [
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = arrayOf("city_id"),
            childColumns = arrayOf("city_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["city_id"])]
)
data class SearchHistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "city_id") val cityId: String,
    @ColumnInfo(name = "search_time") val searchTime: LocalDateTime
)