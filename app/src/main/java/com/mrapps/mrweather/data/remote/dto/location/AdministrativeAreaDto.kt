package com.mrapps.mrweather.data.remote.dto.location

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.location.AdministrativeArea

data class AdministrativeAreaDto(
    @SerializedName("ID") val id: String,
    @SerializedName("LocalizedName") val localizedName: String,
    @SerializedName("EnglishName") val englishName: String,
    @SerializedName("LocalizedType") val localizedType: String,
    @SerializedName("EnglishType") val englishType: String,
    @SerializedName("CountryID") val countryId: String,
) {
    fun toAdministrativeArea(): AdministrativeArea = AdministrativeArea(
        id = this.id,
        localizedName = this.localizedName,
        englishName = this.englishName,
        localizedType = this.localizedType,
        englishType = this.englishType,
        countryId = this.countryId
    )
}