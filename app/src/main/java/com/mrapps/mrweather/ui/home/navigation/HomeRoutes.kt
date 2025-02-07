package com.mrapps.mrweather.ui.home.navigation

sealed class HomeRoutes(val route: String) {
    data object SavedCities : HomeRoutes("savedCities")
    data object Search : HomeRoutes("search")
    data object CityWeather : HomeRoutes("cityWeather/{$ARGUMENT_CITY_ID}") {
        fun createRoute(cityId: String) = "cityWeather/$cityId"
    }
}

const val ARGUMENT_CITY_ID = "city_id"
const val DEFAULT_CITY_ID = "Unknown"