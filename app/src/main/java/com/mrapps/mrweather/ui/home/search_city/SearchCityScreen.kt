package com.mrapps.mrweather.ui.home.search_city

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mrapps.mrweather.R
import com.mrapps.mrweather.domain.model.SearchHistory
import com.mrapps.mrweather.domain.model.SearchHistoryWithCity
import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.ui.animations.AnimationDurations
import com.mrapps.mrweather.ui.home.search_city.components.CityItem
import com.mrapps.mrweather.ui.home.search_city.components.FullScreenLoadingOverlay
import com.mrapps.mrweather.ui.home.search_city.components.SearchHistoryItem
import com.mrapps.mrweather.ui.home.search_city.components.SearchTextField
import com.mrapps.mrweather.ui.theme.MrWeatherTheme
import com.mrapps.mrweather.ui.util.PreviewObjects
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import java.time.Month


@Composable
fun SearchCityScreen(
    viewModel: SearchCityViewModel = koinViewModel(),
    navigateToCityWeather: (cityId: String) -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is SearchScreenEffect.NavigateToCityWeather -> navigateToCityWeather(effect.cityId)
            }
        }
    }

    SearchScreenContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun SearchScreenContent(
    state: SearchCityScreenState,
    onAction: (SearchCityScreenAction) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val history by remember(state.searchHistoryWithCities) {
        derivedStateOf { state.searchHistoryWithCities.map { it.copy() } }
    }
    val cities by remember(state.cities) {
        derivedStateOf { state.cities.map { it.copy() } }
    }

    LaunchedEffect(state.error) {
        if (state.error != null) {
            scope.launch {
                snackbarHostState.showSnackbar("${state.error.message}")
                onAction(SearchCityScreenAction.ClearError)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPaddings ->
        Box(modifier = Modifier.padding(innerPaddings)) {
            Column {
                SearchTextField(
                    query = state.query,
                    onQueryChanged = { onAction(SearchCityScreenAction.QueryChanged(it)) },
                    onSearchClicked = {
                        if (state.query.isNotBlank()) {
                            onAction(SearchCityScreenAction.SearchClicked)
                        }
                    },
                    enabled = !state.isLoading
                )

                if (state.isLoading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                    )
                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                }

                SearchHistoryDisplay(
                    history = history,
                    visible = state.shouldShowHistory(),
                    onCityClick = {
                        onAction(SearchCityScreenAction.CityClicked(it))
                    }
                )

                SearchedCities(
                    cities = cities,
                    onCityClick = {
                        onAction(SearchCityScreenAction.CityClicked(it))
                    }
                )
            }
            FullScreenLoadingOverlay(isLoading = state.isCitySaving)
        }
    }
}

@Composable
private fun SearchHistoryList(
    history: List<SearchHistoryWithCity>,
    onHistoryCityClick: (city: City) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        items(history) { item ->
            SearchHistoryItem(
                city = item.city,
                onClick = { city ->
                    onHistoryCityClick(city)
                }
            )
        }
    }
}

@Composable
private fun SearchedCities(
    cities: List<City>,
    onCityClick: (city: City) -> Unit,
    modifier: Modifier = Modifier,
    animDuration: Int = AnimationDurations.FADE_IN_OUT
) {
    AnimatedVisibility(
        visible = cities.isNotEmpty(),
        enter = fadeIn(animationSpec = tween(animDuration)) + slideInVertically(initialOffsetY = { -100 }),
        exit = fadeOut(animationSpec = tween(animDuration))
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            items(cities) { city ->
                CityItem(
                    city = city,
                    onClick = {
                        onCityClick(it)
                    }
                )
            }
        }
    }
}

@Composable
fun SearchHistoryDisplay(
    modifier: Modifier = Modifier,
    history: List<SearchHistoryWithCity>,
    visible: Boolean,
    animDuration: Int = AnimationDurations.FADE_IN_OUT,
    onCityClick: (city: City) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(animDuration)),
        exit = fadeOut(animationSpec = tween(animDuration))
    ) {
        Column(modifier = modifier.padding(top = 8.dp)) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = stringResource(R.string.recent),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            )
            Spacer(modifier = Modifier.width(8.dp))
            SearchHistoryList(
                history = history,
                onHistoryCityClick = {
                    onCityClick(it)
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
fun SearchScreenContentPreview() {
    MrWeatherTheme {
        SearchScreenContent(
            state = SearchCityScreenState(
                cities = listOf(
                    PreviewObjects.Cities.city,
                    PreviewObjects.Cities.city,
                    PreviewObjects.Cities.city,
                )
            ),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
fun SearchScreenContentLoadingPreview() {
    MrWeatherTheme {
        SearchScreenContent(
            state = SearchCityScreenState(
                cities = listOf(
                    PreviewObjects.Cities.city,
                    PreviewObjects.Cities.city,
                    PreviewObjects.Cities.city,
                ),
                isLoading = true
            ),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
fun SearchScreenContentHistoryPreview() {
    val searchHistory = SearchHistory(
        cityId = PreviewObjects.Cities.city.id,
        searchTime = LocalDateTime.of(2023, Month.MAY, 7, 14, 30, 0, 0)
    )
    val searchHistoryWithCity = SearchHistoryWithCity(
        searchHistory = searchHistory,
        city = PreviewObjects.Cities.city
    )

    MrWeatherTheme {
        SearchScreenContent(
            onAction = {},
            state = SearchCityScreenState(
                searchHistoryWithCities = listOf(
                    searchHistoryWithCity,
                    searchHistoryWithCity,
                    searchHistoryWithCity,
                    searchHistoryWithCity
                )
            )
        )
    }
}