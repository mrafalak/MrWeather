package com.mrapps.mrweather.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme

@Composable
fun ThemeWithSurface(modifier: Modifier = Modifier, content: @Composable () -> Unit = {}) {
    MrWeatherTheme {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}