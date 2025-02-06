package com.mrapps.mrweather.ui.home.search

import androidx.compose.runtime.Composable


@Composable
fun SearchScreen(
    navigateToWeatherDetails: (city: String) -> Unit,
    navigateUp: () -> Unit
) {
    SearchContent()
}

@Composable
fun SearchContent() {
}