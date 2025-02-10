package com.mrapps.mrweather.domain.model.units

enum class MeasurementUnit(val id: Int, val unit: String) {
    SPEED_KMH(7, "km/h"),
    SPEED_MIH(9, "mi/h"),
    TEMPERATURE_C(17, "°C"),
    TEMPERATURE_F(18, "°F"),
    PRESSURE_MB(14, "mb"),
    PRESSURE_INHG(12, "inHg"),
    LENGTH_CM(4, "cm"),
    LENGTH_MM(3, "mm"),
    LENGTH_IN(1, "in")
}