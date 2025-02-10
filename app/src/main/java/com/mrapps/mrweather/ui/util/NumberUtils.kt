package com.mrapps.mrweather.ui.util

import kotlin.math.round

fun roundToSingleDecimal(value: Double): Double {
    return (round(value * 10) / 10)
}

fun convertTemperature(value: Double, metric: Boolean): Pair<Double, Double> {
    return if (metric) value to (value * 9 / 5) + 32 else (value - 32) * 5 / 9 to value
}