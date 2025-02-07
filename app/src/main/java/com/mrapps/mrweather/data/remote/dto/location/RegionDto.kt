package com.mrapps.mrweather.data.remote.dto.location

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.location.Region

data class RegionDto(
    @SerializedName("ID") val id: String,
    @SerializedName("EnglishName") val englishName: String,
    @SerializedName("LocalizedName") val localizedName: String
) {
    fun toRegion(): Region = Region(
        id = this.id,
        englishName = this.englishName,
        localizedName = this.localizedName
    )
}