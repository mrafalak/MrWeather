package com.mrapps.mrweather.ui.util

import kotlin.math.round

fun roundToSingleDecimal(value: Double): Double {
    return (round(value * 10) / 10)
}