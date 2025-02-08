package com.mrapps.mrweather.ui.home.search_city

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
import com.mrapps.mrweather.ui.home.search_city.components.CityItem
import com.mrapps.mrweather.ui.home.search_city.components.SearchHistoryItem
import com.mrapps.mrweather.ui.home.search_city.components.SearchTextField
import com.mrapps.mrweather.ui.home.search_city.components.cityPreview
import com.mrapps.mrweather.ui.theme.MrWeatherTheme
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
        Column(modifier = Modifier.padding(innerPaddings)) {
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
            } else if (state.shouldShowHistory()) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = stringResource(R.string.recent),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    SearchHistoryList(
                        history = state.searchHistoryWithCities,
                        onHistoryCityClick = {
                            onAction(SearchCityScreenAction.CityClicked(it))
                        }
                    )
                }
            } else if (state.cities.isNotEmpty()) {
                SearchedCities(
                    cities = state.cities,
                    onCityClick = {
                        onAction(SearchCityScreenAction.CityClicked(it))
                    }
                )
            }
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
    modifier: Modifier = Modifier
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

@PreviewLightDark
@Composable
fun SearchScreenContentPreview() {
    MrWeatherTheme {
        SearchScreenContent(
            state = SearchCityScreenState(
                cities = listOf(
                    cityPreview,
                    cityPreview,
                    cityPreview,
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
                    cityPreview,
                    cityPreview,
                    cityPreview,
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
        cityId = cityPreview.id,
        searchTime = LocalDateTime.of(2023, Month.MAY, 7, 14, 30, 0, 0)
    )
    val searchHistoryWithCity = SearchHistoryWithCity(
        searchHistory = searchHistory,
        city = cityPreview
    )

    MrWeatherTheme {
        SearchScreenContent(
            state = SearchCityScreenState(searchHistoryWithCities = listOf(searchHistoryWithCity)),
            onAction = {}
        )
    }
}