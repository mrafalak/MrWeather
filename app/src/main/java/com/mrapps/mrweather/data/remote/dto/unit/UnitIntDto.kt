package com.mrapps.mrweather.data.remote.dto.unit

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.units.UnitType

data class UnitIntDto(
    @SerializedName("Unit") val unit: String,
    @SerializedName("UnitType") val unitType: Int,
    @SerializedName("Value") val value: Int
) {
    fun toSmallLength(metric: Boolean): UnitType.SmallLength {
        val mmValue = if (metric) value else value * 25.4
        val inchValue = if (metric) value / 25.4 else value

        return UnitType.SmallLength(mm = mmValue.toDouble(), inch = inchValue.toDouble())
    }

    fun toLength(metric: Boolean): UnitType.Length {
        val cmValue = if (metric) value else value * 2.54
        val inchValue = if (metric) value / 2.54 else value

        return UnitType.Length(cm = cmValue.toDouble(), inch = inchValue.toDouble())
    }
}