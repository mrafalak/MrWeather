package com.mrapps.mrweather.ui.home.city_weather.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mrapps.mrweather.R
import com.mrapps.mrweather.domain.model.forecast.DailyForecast
import com.mrapps.mrweather.domain.model.forecast.TemperaturesRange
import com.mrapps.mrweather.domain.model.units.UnitSystemType
import com.mrapps.mrweather.domain.model.weather_condition.WeatherIconType
import com.mrapps.mrweather.ui.animations.AnimationDurations
import com.mrapps.mrweather.ui.animations.shimmerEffect
import com.mrapps.mrweather.ui.theme.MrWeatherTheme
import com.mrapps.mrweather.ui.util.PreviewObjects
import com.mrapps.mrweather.ui.util.getFontColorWithMetricTemperature
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ForecastInfo(
    modifier: Modifier = Modifier,
    forecast: List<DailyForecast>? = null,
    isLoading: Boolean,
    unitSystemType: UnitSystemType,
) {
    Column(
        modifier
            .fillMaxWidth()
            .height(300.dp),
        verticalArrangement = Arrangement.Center
    ) {
        ForecastLoading(isLoading = isLoading)
        ForecastNoData(
            forecastIsNull = forecast == null,
            isLoading = isLoading
        )
        if (forecast != null) {
            ForecastDisplay(
                forecast = forecast,
                isLoading = isLoading,
                unitSystemType = unitSystemType
            )
        }
    }
}

@Composable
fun ForecastLoading(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    animDuration: Int = AnimationDurations.FADE_IN_OUT
) {
    AnimatedVisibility(
        visible = isLoading,
        enter = fadeIn(animationSpec = tween(animDuration)),
        exit = fadeOut(animationSpec = tween(animDuration))
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .shimmerEffect(MaterialTheme.colorScheme.onSurface),
            contentAlignment = Alignment.Center
        ) {}
    }
}

@Composable
fun ForecastNoData(
    modifier: Modifier = Modifier,
    forecastIsNull: Boolean,
    isLoading: Boolean,
    animDuration: Int = AnimationDurations.FADE_IN_OUT
) {
    val isPreview = LocalInspectionMode.current
    var hasTriedFetching by remember { mutableStateOf(isPreview) }

    LaunchedEffect(isLoading) {
        if (isLoading) hasTriedFetching = true
    }

    AnimatedVisibility(
        visible = hasTriedFetching && forecastIsNull && !isLoading,
        enter = fadeIn(animationSpec = tween(animDuration)),
        exit = fadeOut(animationSpec = tween(animDuration))
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    imageVector = Icons.Default.Warning,
                    contentDescription = stringResource(R.string.no_forecast_data_image),
                    modifier = Modifier
                        .size(64.dp)
                        .padding(bottom = 8.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                )
                Text(
                    text = stringResource(R.string.forecast_no_data_available),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Composable
fun ForecastDisplay(
    modifier: Modifier = Modifier,
    forecast: List<DailyForecast>,
    isLoading: Boolean,
    unitSystemType: UnitSystemType,
    animDuration: Int = AnimationDurations.FADE_IN_OUT
) {
    val isPreview = LocalInspectionMode.current

    var isContentVisible by remember { mutableStateOf(isPreview) }

    LaunchedEffect(isLoading, forecast) {
        isContentVisible = !isLoading
    }

    AnimatedVisibility(
        visible = isContentVisible,
        enter = fadeIn(animationSpec = tween(animDuration)) + slideInHorizontally(
            initialOffsetX = { -it }
        ),
        exit = fadeOut(animationSpec = tween(animDuration))
    ) {
        LazyRow(
            modifier = modifier
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(forecast) { dailyForecast ->
                ForecastDay(
                    dailyForecast = dailyForecast,
                    unitSystemType = unitSystemType
                )
            }
        }
    }
}

@Composable
fun ForecastDay(
    modifier: Modifier = Modifier,
    dailyForecast: DailyForecast,
    unitSystemType: UnitSystemType
) {
    Card(
        modifier = modifier
            .height(300.dp)
            .width(264.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (dailyForecast.temperatures == null || dailyForecast.realFeelTemperature == null || dailyForecast.realFeelTemperatureShade == null) {
                ForecastNoData(forecastIsNull = true, isLoading = false)
            } else {
                DateDisplay(date = dailyForecast.date)
                Spacer(modifier = Modifier.height(8.dp))
                DayNightImages(
                    dayIcon = dailyForecast.day.icon,
                    nightIcon = dailyForecast.night.icon
                )
                Spacer(modifier = Modifier.height(8.dp))
                TemperatureDisplay(
                    temperatures = dailyForecast.temperatures,
                    unitSystemType = unitSystemType
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RealFeelTemperatureDisplay(
                        temperatures = dailyForecast.realFeelTemperature,
                        unitSystemType = unitSystemType
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    RealFeelShadeTemperatureDisplay(
                        temperatures = dailyForecast.realFeelTemperatureShade,
                        unitSystemType = unitSystemType
                    )
                }
            }
        }
    }
}

@Composable
private fun DateDisplay(
    modifier: Modifier = Modifier,
    date: LocalDateTime
) {
    val formattedDate = remember(date) {
        date.format(DateTimeFormatter.ofPattern("EEEE, dd.MM", Locale.getDefault()))
            .replaceFirstChar { it.uppercaseChar() }
    }

    Text(
        modifier = modifier.fillMaxWidth(),
        text = formattedDate,
        textAlign = TextAlign.Center
    )
}

@Composable
fun DayNightImages(
    modifier: Modifier = Modifier,
    dayIcon: WeatherIconType,
    nightIcon: WeatherIconType,
) {
    Row(modifier = modifier) {
        WeatherImage(modifier = Modifier.weight(1f), weatherType = dayIcon)
        WeatherImage(modifier = Modifier.weight(1f), weatherType = nightIcon)
    }
}

@Composable
private fun TemperatureDisplay(
    modifier: Modifier = Modifier,
    temperatures: TemperaturesRange,
    unitSystemType: UnitSystemType
) {
    val defaultColor = MaterialTheme.colorScheme.onBackground
    val (minTemperature, maxTemperature) = getTemperatureValues(
        unitSystemType,
        temperatures
    )
    val (minTemperatureUnit, maxTemperatureUnit) = getTemperatureUnits(
        unitSystemType,
        temperatures
    )
    val minTemperatureColor = getFontColorWithMetricTemperature(
        temperature = minTemperature,
        defaultColor = defaultColor
    )

    val maxTemperatureColor = getFontColorWithMetricTemperature(
        temperature = maxTemperature,
        defaultColor = defaultColor
    )

    Row(modifier = modifier) {
        Text(
            text = "$maxTemperature$maxTemperatureUnit",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = maxTemperatureColor
        )
        Text(
            text = " / ",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1
        )
        Text(
            text = "$minTemperature$minTemperatureUnit",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = minTemperatureColor,
            maxLines = 1
        )
    }
}

@Composable
fun RealFeelTemperatureDisplay(
    modifier: Modifier = Modifier,
    temperatures: TemperaturesRange,
    unitSystemType: UnitSystemType
) {
    val (minRealFeel, maxRealFeel) = getTemperatureValues(
        unitSystemType,
        temperatures
    )
    val (minRealFeelUnit, maxRealFeelUnit) = getTemperatureUnits(
        unitSystemType,
        temperatures
    )

    TemperatureLabel(
        modifier = modifier,
        label = stringResource(R.string.real_feel_label),
        temperature = "$maxRealFeel$maxRealFeelUnit / $minRealFeel$minRealFeelUnit"
    )
}

@Composable
fun RealFeelShadeTemperatureDisplay(
    modifier: Modifier = Modifier,
    temperatures: TemperaturesRange,
    unitSystemType: UnitSystemType
) {
    val (minRealFeelShade, maxRealFeelShade) = getTemperatureValues(
        unitSystemType,
        temperatures
    )
    val (minRealFeelShadeUnit, maxRealFeelShadeUnit) = getTemperatureUnits(
        unitSystemType,
        temperatures
    )

    TemperatureLabel(
        modifier = modifier,
        label = stringResource(R.string.real_feel_shade_label),
        temperature = "$maxRealFeelShade$maxRealFeelShadeUnit / $minRealFeelShade$minRealFeelShadeUnit"
    )
}

@Composable
fun TemperatureLabel(
    modifier: Modifier = Modifier,
    label: String, temperature: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            maxLines = 1
        )
        Text(
            text = temperature,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1
        )
    }
}

@Composable
fun getTemperatureValues(
    unitSystemType: UnitSystemType,
    temperaturesRange: TemperaturesRange
): Pair<Double, Double> {
    return remember(unitSystemType) {
        derivedStateOf {
            when (unitSystemType) {
                UnitSystemType.METRIC -> temperaturesRange.minimum.metric.value to temperaturesRange.maximum.metric.value
                UnitSystemType.IMPERIAL -> temperaturesRange.minimum.imperial.value to temperaturesRange.maximum.imperial.value
            }
        }
    }.value
}

@Composable
fun getTemperatureUnits(
    unitSystemType: UnitSystemType,
    temperaturesRange: TemperaturesRange
): Pair<String, String> {
    return remember(unitSystemType) {
        derivedStateOf {
            when (unitSystemType) {
                UnitSystemType.METRIC -> temperaturesRange.minimum.metric.unit.unit to temperaturesRange.maximum.metric.unit.unit
                UnitSystemType.IMPERIAL -> temperaturesRange.minimum.imperial.unit.unit to temperaturesRange.maximum.imperial.unit.unit
            }
        }
    }.value
}


@PreviewLightDark
@Composable
fun ForecastInfoPreview() {
    MrWeatherTheme {
        ForecastInfo(
            forecast = PreviewObjects.fiveDaysForecast,
            isLoading = false,
            unitSystemType = UnitSystemType.METRIC
        )
    }
}

@PreviewLightDark
@Composable
fun ForecastDayPreview() {
    MrWeatherTheme {
        ForecastDay(
            dailyForecast = PreviewObjects.fiveDaysForecast[0],
            unitSystemType = UnitSystemType.METRIC
        )
    }
}