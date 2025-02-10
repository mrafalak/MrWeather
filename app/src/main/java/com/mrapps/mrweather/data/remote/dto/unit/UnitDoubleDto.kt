package com.mrapps.mrweather.data.remote.dto.unit

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.units.UnitType

data class UnitDoubleDto(
    @SerializedName("Unit") val unit: String,
    @SerializedName("UnitType") val unitType: Int,
    @SerializedName("Value") val value: Double?
) {
    fun toLength(metric: Boolean): UnitType.Length? {
        return if (value == null) {
            null
        } else {
            val cmValue = if (metric) value else value * 2.54
            val inchValue = if (metric) value / 2.54 else value

            UnitType.Length(cm = cmValue, inch = inchValue)
        }
    }

    fun toSmallLength(metric: Boolean): UnitType.SmallLength? {
        return if (value == null) {
            null
        } else {
            val mmValue = if (metric) value else value * 25.4
            val inchValue = if (metric) value / 25.4 else value

            UnitType.SmallLength(mm = mmValue, inch = inchValue)
        }
    }
}