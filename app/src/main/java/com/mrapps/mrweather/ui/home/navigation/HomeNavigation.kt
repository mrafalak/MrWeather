package com.mrapps.mrweather.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mrapps.mrweather.ui.home.search_city.SearchCityScreen
import com.mrapps.mrweather.ui.home.city_weather.CityWeatherScreen

fun NavGraphBuilder.addHomeNavGraph(navController: NavHostController) {
    composable(HomeRoutes.Search.route) {
        SearchCityScreen(
            navigateToCityWeather = { cityId ->
                navController.navigate(HomeRoutes.CityWeather.createRoute(cityId))
            }
        )
    }
    composable(HomeRoutes.CityWeather.route) { backStackEntry ->
        CityWeatherScreen(
            cityId = backStackEntry.arguments?.getString(ARGUMENT_CITY_ID) ?: DEFAULT_CITY_ID,
            navigateBack = {
                navController.popBackStack()
            }
        )
    }
}