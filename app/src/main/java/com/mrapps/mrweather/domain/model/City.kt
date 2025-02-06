package com.mrapps.mrweather.domain.model


data class City(
    val id: String,
    val localizedName: String,
    val englishName: String,
    val administrativeArea: AdministrativeArea,
    val country: Country,
    val region: Region,
)