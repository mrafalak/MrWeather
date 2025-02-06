package com.mrapps.mrweather.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.City

data class CityDto(
    @SerializedName("Key") val id: String,
    @SerializedName("LocalizedName") val localizedName: String,
    @SerializedName("EnglishName") val englishName: String,
    @SerializedName("AdministrativeArea") val administrativeArea: AdministrativeAreaDto,
    @SerializedName("Country") val country: CountryDto,
    @SerializedName("Region") val region: RegionDto,
) {
    fun toCity(): City = City(
        id = this.id,
        localizedName = this.localizedName,
        englishName = this.englishName,
        administrativeArea = this.administrativeArea.toAdministrativeArea(),
        country = this.country.toCountry(),
        region = this.region.toRegion(),
    )
}

fun List<CityDto>.toCityList() = map { it.toCity() }