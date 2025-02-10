package com.mrapps.mrweather.ui.util

import androidx.compose.ui.graphics.Color
import com.mrapps.mrweather.ui.theme.Blue
import com.mrapps.mrweather.ui.theme.Red

fun getFontColorWithMetricTemperature(temperature: Double, defaultColor: Color): Color {
    return when {
        temperature < 10 -> Blue
        temperature in 10.0..20.0 -> defaultColor
        else -> Red
    }
}