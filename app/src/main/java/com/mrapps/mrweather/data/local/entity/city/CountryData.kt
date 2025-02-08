package com.mrapps.mrweather.data.local.entity.city

import androidx.room.ColumnInfo

data class CountryData(
    @ColumnInfo(name = "country_id") val id: String,
    @ColumnInfo(name = "country_localized_name") val localizedName: String,
    @ColumnInfo(name = "country_english_name") val englishName: String,
)