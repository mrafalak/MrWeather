package com.mrapps.mrweather.domain.model.units


sealed class Imperial(val unit: ImperialUnit, open val value: Double) {
    data class SpeedMIH(override val value: Double) : Imperial(ImperialUnit.SPEED_MIH, value)
    data class Temperature(override val value: Double) : Imperial(ImperialUnit.TEMPERATURE, value)
    data class Pressure(override val value: Double) : Imperial(ImperialUnit.PRESSURE, value)
    data class Unknown(override val value: Double) : Imperial(ImperialUnit.UNKNOWN, value)

    companion object {
        fun fromUnitType(type: Int, value: Double): Imperial {
            return when (type) {
                ImperialUnit.SPEED_MIH.type -> SpeedMIH(value)
                ImperialUnit.TEMPERATURE.type -> Temperature(value)
                ImperialUnit.PRESSURE.type -> Pressure(value)
                else -> Unknown(value)
            }
        }
    }
}

enum class ImperialUnit(val type: Int, val unit: String) {
    SPEED_MIH(7, "mi/h"),
    TEMPERATURE(18, "F"),
    PRESSURE(14, "inHg"),
    UNKNOWN(-1, "UnsupportedUnit")
}