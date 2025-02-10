package com.mrapps.mrweather.domain.model.weather_condition

import com.mrapps.mrweather.R


enum class PrecipitationType(val value: String?, val resId: Int) {
    RAIN("Rain", R.string.precipitation_type_rain),
    SNOW("Snow", R.string.precipitation_type_snow),
    ICE("Ice", R.string.precipitation_type_ice),
    MIXED("Mixed", R.string.precipitation_type_mixed),
    NONE(null, R.string.precipitation_type_none),
    UNKNOWN("Unknown", R.string.precipitation_type_unknown);

    companion object {
        fun fromValue(value: String?): PrecipitationType {
            return entries.find { it.value == value } ?: UNKNOWN
        }
    }
}