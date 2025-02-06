package com.mrapps.mrweather.ui.home.navigation

sealed class HomeRoutes(val route: String) {
    data object SavedCities : HomeRoutes("savedCities")
    data object Search : HomeRoutes("search")
    data object CityWeather : HomeRoutes("cityWeather/{city}") {
        fun createRoute(city: String) = "cityWeather/$city"
    }
}