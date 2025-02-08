package com.mrapps.mrweather.data.local.entity.city

import androidx.room.ColumnInfo

data class AdministrativeAreaData(
    @ColumnInfo(name = "area_id") val id: String,
    @ColumnInfo(name = "area_localized_name") val localizedName: String,
    @ColumnInfo(name = "area_english_name") val englishName: String,
    @ColumnInfo(name = "localized_type") val localizedType: String,
    @ColumnInfo(name = "english_type") val englishType: String,
)