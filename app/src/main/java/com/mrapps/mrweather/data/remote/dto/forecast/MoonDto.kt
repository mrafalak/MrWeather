package com.mrapps.mrweather.data.remote.dto.forecast

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.forecast.Moon

data class MoonDto(
    @SerializedName("Age") val age: Int,
    @SerializedName("EpochRise") val epochRise: Int,
    @SerializedName("EpochSet") val epochSet: Int,
    @SerializedName("Phase") val phase: String,
    @SerializedName("Rise") val rise: String,
    @SerializedName("Set") val set: String
) {
    fun toMoon(): Moon = Moon(
        age = age,
        epochRise = epochRise,
        epochSet = epochSet,
        phase = phase,
        rise = rise,
        set = set
    )
}