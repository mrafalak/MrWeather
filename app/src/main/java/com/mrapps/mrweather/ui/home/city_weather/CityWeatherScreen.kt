package com.mrapps.mrweather.ui.home.city_weather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mrapps.mrweather.R
import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.ui.animations.AnimationDurations
import com.mrapps.mrweather.ui.animations.shimmerEffect
import com.mrapps.mrweather.ui.home.city_weather.components.ForecastInfo
import com.mrapps.mrweather.ui.util.PreviewObjects
import com.mrapps.mrweather.ui.home.city_weather.components.WeatherConditionsInfo
import com.mrapps.mrweather.ui.home.search_city.components.cityPreview
import com.mrapps.mrweather.ui.theme.MrWeatherTheme
import com.mrapps.mrweather.ui.util.PreviewObjects.conditionsPreview
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun CityWeatherScreen(
    cityIdArg: String,
    viewModel: CityWeatherViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(cityIdArg) {
        if (state.cityId == null) {
            viewModel.onAction(CityWeatherScreenAction.FetchCityData(cityIdArg))
            viewModel.onAction(CityWeatherScreenAction.FetchForecast(cityIdArg))
            viewModel.onAction(CityWeatherScreenAction.FetchCurrentConditions(cityIdArg))
        }
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
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(state.error) {
        if (state.error != null) {
            scope.launch {
                snackbarHostState.showSnackbar("${state.error.message}")
                onAction(CityWeatherScreenAction.ClearError)
            }
        }
    }

    Scaffold(
        topBar = {
            CityWeatherTopAppBar(
                cityState = state.city,
                isCityLoading = state.isCityLoading,
                navigateBack = {
                    onAction(CityWeatherScreenAction.NavigateBack)
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .padding(innerPaddings)
                .padding(8.dp)
        ) {
            WeatherConditionsInfo(
                weatherConditionsState = state.weatherConditions,
                isLoading = state.isConditionsLoading
            )
            ForecastInfo(
                modifier = Modifier.padding(top = 16.dp),
                forecast = state.forecast,
                isLoading = state.isForecastLoading,
                unitSystemType = state.unitSystemType
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityWeatherTopAppBar(
    cityState: City? = null,
    isCityLoading: Boolean = false,
    navigateBack: () -> Unit
) {
    val city = remember(cityState?.id) { cityState }

    TopAppBar(
        title = {
            Box(modifier = Modifier.height(42.dp)) {
                TopBarLoading(isCityLoading = isCityLoading)
                TopBarError(
                    isCityLoading = isCityLoading,
                    isCityNull = city == null
                )
                TopBarCity(
                    city = city,
                    isCityLoading = isCityLoading
                )
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

@Composable
fun TopBarLoading(
    modifier: Modifier = Modifier,
    isCityLoading: Boolean,
    animDuration: Int = AnimationDurations.FADE_IN_OUT
) {
    AnimatedVisibility(
        visible = isCityLoading,
        enter = fadeIn(animationSpec = tween(animDuration)),
        exit = fadeOut(animationSpec = tween(animDuration))
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .shimmerEffect(MaterialTheme.colorScheme.onSurface),
        ) {}
    }
}

@Composable
fun TopBarError(
    modifier: Modifier = Modifier,
    isCityLoading: Boolean,
    isCityNull: Boolean,
    animDuration: Int = AnimationDurations.FADE_IN_OUT
) {
    val isPreview = LocalInspectionMode.current
    var hasTriedFetching by remember { mutableStateOf(isPreview) }

    LaunchedEffect(isCityLoading) {
        if (isCityLoading) hasTriedFetching = true
    }

    AnimatedVisibility(
        visible = hasTriedFetching && !isCityLoading && isCityNull,
        enter = fadeIn(animationSpec = tween(animDuration)) + slideInVertically { it },
        exit = fadeOut(animationSpec = tween(animDuration)) + slideOutVertically { -it }
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.city_loading_warning),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.Warning,
                contentDescription = stringResource(R.string.warning_ic),
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun TopBarCity(
    modifier: Modifier = Modifier,
    city: City?,
    isCityLoading: Boolean,
    animDuration: Int = AnimationDurations.FADE_IN_OUT
) {
    AnimatedVisibility(
        visible = !isCityLoading && city != null,
        enter = fadeIn(animationSpec = tween(animDuration)) + slideInVertically { it },
        exit = fadeOut(animationSpec = tween(animDuration)) + slideOutVertically { -it }
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            Text(
                text = city?.localizedName ?: "",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${city?.administrativeArea?.localizedName ?: ""}, " +
                        "${city?.country?.localizedName ?: ""}, " +
                        (city?.region?.localizedName ?: ""),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CityWeatherContentPreview(modifier: Modifier = Modifier) {
    MrWeatherTheme {
        CityWeatherContent(
            state = CityWeatherScreenState(
                city = cityPreview,
                weatherConditions = conditionsPreview.copy(
                    temperature = conditionsPreview.temperature?.copy(
                        metricValue = 21.0
                    )
                ),
                forecast = PreviewObjects.fiveDaysForecast,
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