package com.mrapps.mrweather.data.local.entity.city

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey
    @ColumnInfo(name = "city_id") val id: String,
    @ColumnInfo(name = "city_localized_name") val localizedName: String,
    @ColumnInfo(name = "city_english_name") val englishName: String,
    @Embedded val administrativeAreaData: AdministrativeAreaData,
    @Embedded val country: CountryData,
    @Embedded val region: RegionData,
)