package com.mrapps.mrweather.ui.home.city_weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel


@Composable
fun CityWeatherScreen(
    cityId: String,
    viewModel: CityWeatherViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(cityId) {
        viewModel.onAction(CityWeatherScreenAction.FetchCurrentConditions(cityId))
    }

    CityWeatherContent(
        state = state,
        onAction = { action ->
            when (action) {
                CityWeatherScreenAction.NavigateBack -> navigateBack()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun CityWeatherContent(
    state: CityWeatherScreenState,
    onAction: (CityWeatherScreenAction) -> Unit
) {
}