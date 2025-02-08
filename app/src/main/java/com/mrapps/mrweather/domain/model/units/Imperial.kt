package com.mrapps.mrweather.domain.model.units


sealed class Imperial(val unit: ImperialUnit, open val value: Double) {
    data class SpeedMIH(override val value: Double) : Imperial(ImperialUnit.SPEED_MIH, value)
    data class Temperature(override val value: Double) : Imperial(ImperialUnit.TEMPERATURE, value)
    data class Pressure(override val value: Double) : Imperial(ImperialUnit.PRESSURE, value)
    data class Unknown(override val value: Double) : Imperial(ImperialUnit.UNKNOWN, value)
}

enum class ImperialUnit(val type: Int, val unit: String) {
    SPEED_MIH(9, "mi/h"),
    TEMPERATURE(18, "F"),
    PRESSURE(12, "inHg"),
    UNKNOWN(-1, "???"),
}

object ImperialFactory {
    fun fromUnitType(type: Int, value: Double): Imperial {
        return when (ImperialUnit.entries.find { it.type == type }) {
            ImperialUnit.SPEED_MIH -> Imperial.SpeedMIH(value)
            ImperialUnit.TEMPERATURE -> Imperial.Temperature(value)
            ImperialUnit.PRESSURE -> Imperial.Pressure(value)
            ImperialUnit.UNKNOWN -> Imperial.Unknown(value)
            else -> Imperial.Unknown(value)
        }
    }
}