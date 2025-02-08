package com.mrapps.mrweather.ui.home.city_weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mrapps.mrweather.R
import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.ui.home.city_weather.components.WeatherConditionsInfo
import com.mrapps.mrweather.ui.home.city_weather.components.conditionsPreview
import com.mrapps.mrweather.ui.home.search_city.components.cityPreview
import com.mrapps.mrweather.ui.theme.MrWeatherTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun CityWeatherScreen(
    cityId: String,
    viewModel: CityWeatherViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(cityId) {
        viewModel.onAction(CityWeatherScreenAction.FetchCityData(cityId))
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
    Scaffold(
        topBar = {
            CityWeatherTopAppBar(
                city = state.city,
                isCityLoading = state.isCityLoading,
                navigateBack = {
                    onAction(CityWeatherScreenAction.NavigateBack)
                }
            )
        }
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .padding(innerPaddings)
                .padding(8.dp)
        ) {
            WeatherConditionsInfo(
                weatherConditions = state.weatherConditions,
                isLoading = state.isConditionsLoading
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityWeatherTopAppBar(
    city: City? = null,
    isCityLoading: Boolean = false,
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            if (isCityLoading) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.city_loading),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            } else if (city == null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = stringResource(R.string.warning_ic),
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.city_loading_warning),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            } else {
                Column {
                    Text(
                        text = city.localizedName,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${city.administrativeArea.localizedName}, " +
                                "${city.country.localizedName}, " +
                                city.region.localizedName,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.navigate_back_ic)
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
fun CityWeatherContentPreview(modifier: Modifier = Modifier) {
    MrWeatherTheme {
        CityWeatherContent(
            state = CityWeatherScreenState(
                city = cityPreview,
                weatherConditions = conditionsPreview,
                isCityLoading = false,
                isConditionsLoading = false,
                error = null
            )
        ) { }
    }
}

@PreviewLightDark
@Composable
fun CityWeatherContentLoadingPreview(modifier: Modifier = Modifier) {
    MrWeatherTheme {
        CityWeatherContent(
            state = CityWeatherScreenState(
                isCityLoading = true,
                isConditionsLoading = true
            )
        ) { }
    }
}

@PreviewLightDark
@Composable
fun CityWeatherContentNoDataPreview(modifier: Modifier = Modifier) {
    MrWeatherTheme {
        CityWeatherContent(state = CityWeatherScreenState()) { }
    }
}