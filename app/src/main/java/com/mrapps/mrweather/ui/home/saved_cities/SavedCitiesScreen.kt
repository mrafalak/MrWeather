package com.mrapps.mrweather.ui.home.saved_cities

import androidx.compose.runtime.Composable


@Composable
fun SavedCitiesScreen(
    navigateToWeatherDetails: (city: String) -> Unit,
    navigateToSearchCity: () -> Unit
) {
    SavedCitiesContent()
}


@Composable
fun SavedCitiesContent() {
}