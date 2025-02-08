package com.mrapps.mrweather.data.local.entity.city

import androidx.room.ColumnInfo

data class RegionData(
    @ColumnInfo(name = "region_id") val id: String,
    @ColumnInfo(name = "region_localized_name") val localizedName: String,
    @ColumnInfo(name = "region_english_name") val englishName: String,
)