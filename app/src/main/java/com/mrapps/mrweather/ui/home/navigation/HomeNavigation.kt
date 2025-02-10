package com.mrapps.mrweather.ui.home.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
    composable(HomeRoutes.CityWeather.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it }
            ) + fadeIn()
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it }
            ) + fadeOut()
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it }
            ) + fadeIn()
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it }
            ) + fadeOut()
        }) { backStackEntry ->
        CityWeatherScreen(
            cityIdArg = backStackEntry.arguments?.getString(ARGUMENT_CITY_ID) ?: DEFAULT_CITY_ID,
            navigateBack = {
                navController.popBackStack()
            }
        )
    }
}