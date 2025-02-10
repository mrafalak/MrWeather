package com.mrapps.mrweather.data.remote.dto.forecast

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.forecast.RelativeHumidity

data class RelativeHumidityDto(
    @SerializedName("Average") val average: Int,
    @SerializedName("Maximum") val maximum: Int,
    @SerializedName("Minimum") val minimum: Int
) {
    fun toRelativeHumidity(): RelativeHumidity = RelativeHumidity(
        average = average,
        maximum = maximum,
        minimum = minimum
    )
}