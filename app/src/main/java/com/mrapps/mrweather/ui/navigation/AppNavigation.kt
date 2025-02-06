package com.mrapps.mrweather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.mrapps.mrweather.ui.home.navigation.HomeRoutes
import com.mrapps.mrweather.ui.home.navigation.addHomeNavGraph

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = GraphRoutes.Home.route) {
        navigation(startDestination = HomeRoutes.SavedCities.route, route = GraphRoutes.Home.route) {
            addHomeNavGraph(navController)
        }
    }
}