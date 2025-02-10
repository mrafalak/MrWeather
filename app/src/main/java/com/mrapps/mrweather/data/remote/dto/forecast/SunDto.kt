package com.mrapps.mrweather.data.remote.dto.forecast

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.forecast.Sun

data class SunDto(
    @SerializedName("EpochRise") val epochRise: Int,
    @SerializedName("EpochSet") val epochSet: Int,
    @SerializedName("Rise") val rise: String,
    @SerializedName("Set") val set: String
) {
    fun toSun(): Sun = Sun(
        epochRise = epochRise,
        epochSet = epochSet,
        rise = rise,
        set = set
    )
}