package com.mrapps.mrweather.domain.model.weather_condition


enum class PressureTendency(val value: String) {
    FALLING("F"),
    STEADY("S"),
    RISING("R"),
    UNKNOWN("Unknown");

    companion object {
        fun fromValue(value: String): PressureTendency {
            return entries.find { it.value == value } ?: UNKNOWN
        }
    }
}