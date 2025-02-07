package com.mrapps.mrweather.domain.model.units


sealed class Metric(val unit: MetricUnit, open val value: Double) {
    data class SpeedKMH(override val value: Double) : Metric(MetricUnit.SPEED_KMH, value)
    data class Temperature(override val value: Double) : Metric(MetricUnit.TEMPERATURE, value)
    data class Pressure(override val value: Double) : Metric(MetricUnit.PRESSURE, value)
    data class Unknown(override val value: Double) : Metric(MetricUnit.UNKNOWN, value)

    companion object {
        fun fromUnitType(type: Int, value: Double): Metric {
            return when (type) {
                MetricUnit.SPEED_KMH.type -> SpeedKMH(value)
                MetricUnit.TEMPERATURE.type -> Temperature(value)
                MetricUnit.PRESSURE.type -> Pressure(value)
                else -> Unknown(value)
            }
        }
    }
}

enum class MetricUnit(val type: Int, val unit: String) {
    SPEED_KMH(7, "km/h"),
    TEMPERATURE(17, "C"),
    PRESSURE(14, "mb"),
    UNKNOWN(-1, "UnsupportedUnit ")
}