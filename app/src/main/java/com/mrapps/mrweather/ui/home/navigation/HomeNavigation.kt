package com.mrapps.mrweather.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mrapps.mrweather.ui.home.saved_cities.SavedCitiesScreen
import com.mrapps.mrweather.ui.home.search.SearchScreen
import com.mrapps.mrweather.ui.home.city_weather.CityWeatherScreen

fun NavGraphBuilder.addHomeNavGraph(navController: NavHostController) {
    composable(HomeRoutes.SavedCities.route) {
        SavedCitiesScreen(
            navigateToWeatherDetails = { city ->
                navController.navigate(HomeRoutes.CityWeather.createRoute(city))
            },
            navigateToSearchCity = {
                navController.navigate(HomeRoutes.Search.route)
            }
        )
    }
    composable(HomeRoutes.Search.route) {
        SearchScreen(
            navigateToWeatherDetails = { cityId ->
                navController.navigate(HomeRoutes.CityWeather.createRoute(cityId))
            },
            navigateBack = {
                navController.popBackStack()
            },
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