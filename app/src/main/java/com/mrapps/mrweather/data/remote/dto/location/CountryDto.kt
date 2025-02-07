package com.mrapps.mrweather.data.remote.dto.location

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.location.Country

data class CountryDto(
    @SerializedName("ID") val id: String,
    @SerializedName("EnglishName") val englishName: String,
    @SerializedName("LocalizedName") val localizedName: String
) {
    fun toCountry(): Country = Country(
        id = this.id,
        englishName = this.englishName,
        localizedName = this.localizedName
    )
}