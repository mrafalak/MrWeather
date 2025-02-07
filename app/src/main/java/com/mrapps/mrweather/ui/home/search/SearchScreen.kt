package com.mrapps.mrweather.ui.home.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
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
import com.mrapps.mrweather.domain.model.City
import com.mrapps.mrweather.ui.home.search.components.SearchTextField
import com.mrapps.mrweather.ui.theme.MrWeatherTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    navigateToWeatherDetails: (cityId: String) -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    SearchScreenContent(
        state = state,
        onAction = { action ->
            when (action) {
                SearchAction.NavigateBack -> navigateBack()
                is SearchAction.CityClicked -> {
                    navigateToWeatherDetails(action.city.id)
                }

                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun SearchScreenContent(
    state: SearchScreenState,
    onAction: (SearchAction) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(state.error) {
        if (state.error != null) {
            scope.launch {
                snackbarHostState.showSnackbar("${state.error.message}")
                onAction.invoke(SearchAction.ClearError)
            }
        }
    }

    Scaffold(
        topBar = { SearchTopBar { onAction(SearchAction.NavigateBack) } },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPaddings ->
        Column(modifier = Modifier.padding(innerPaddings)) {
            SearchTextField(
                query = state.query,
                onQueryChanged = { onAction.invoke(SearchAction.QueryChanged(it)) },
                onSearchClicked = {
                    if (state.query.isNotBlank()) {
                        onAction.invoke(SearchAction.SearchClicked)
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
            } else if (state.cities.isNotEmpty()) {
                SearchedCities(
                    cities = state.cities,
                    onCityClick = {
                        onAction(SearchAction.CityClicked(it))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchTopBar(navigateBack: () -> Unit) {
    TopAppBar(title = {}, navigationIcon = {
        IconButton(onClick = navigateBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.navigate_back_ic)
            )
        }
    })
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
fun SearchScreenContentPreview(modifier: Modifier = Modifier) {
    MrWeatherTheme {
        SearchScreenContent(
            state = SearchScreenState(
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
fun SearchScreenContentLoadingPreview(modifier: Modifier = Modifier) {
    MrWeatherTheme {
        SearchScreenContent(
            state = SearchScreenState(
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