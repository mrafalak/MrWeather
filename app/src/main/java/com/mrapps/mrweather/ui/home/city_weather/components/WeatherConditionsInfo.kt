package com.mrapps.mrweather.ui.home.city_weather.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mrapps.mrweather.R
import com.mrapps.mrweather.domain.model.units.UnitSystemType
import com.mrapps.mrweather.domain.model.units.UnitType
import com.mrapps.mrweather.domain.model.units.formatToString
import com.mrapps.mrweather.domain.model.weather_condition.PrecipitationType
import com.mrapps.mrweather.domain.model.weather_condition.WeatherConditions
import com.mrapps.mrweather.domain.model.weather_condition.WeatherIconType
import com.mrapps.mrweather.domain.model.weather_condition.Wind
import com.mrapps.mrweather.domain.util.formatTimeToString
import com.mrapps.mrweather.ui.animations.AnimationDurations
import com.mrapps.mrweather.ui.animations.shimmerEffect
import com.mrapps.mrweather.ui.util.PreviewObjects.conditionsPreview
import com.mrapps.mrweather.ui.theme.MrWeatherTheme
import com.mrapps.mrweather.ui.util.getFontColorWithMetricTemperature
import java.time.LocalDateTime

@Composable
fun WeatherConditionsInfo(
    modifier: Modifier = Modifier,
    weatherConditionsState: WeatherConditions? = null,
    unitSystemType: UnitSystemType = UnitSystemType.METRIC,
    isLoading: Boolean = false
) {
    val weatherConditions = remember(weatherConditionsState?.epochTime) { weatherConditionsState }

    Box(
        modifier
            .fillMaxWidth()
            .heightIn(max = 264.dp)
    ) {
        WeatherConditionsLoading(isLoading = isLoading)
        WeatherConditionsNoData(
            weatherConditionsIsNull = weatherConditions == null,
            isLoading = isLoading
        )
        if (weatherConditions != null) {
            WeatherConditionsDisplay(
                weatherConditions = weatherConditions,
                unitSystemType = unitSystemType,
                isLoading = isLoading
            )
        }
    }
}

@Composable
private fun WeatherConditionsLoading(
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
private fun WeatherConditionsNoData(
    modifier: Modifier = Modifier,
    weatherConditionsIsNull: Boolean,
    isLoading: Boolean,
    animDuration: Int = AnimationDurations.FADE_IN_OUT
) {
    val isPreview = LocalInspectionMode.current
    var hasTriedFetching by remember { mutableStateOf(isPreview) }

    LaunchedEffect(isLoading) {
        if (isLoading) hasTriedFetching = true
    }

    AnimatedVisibility(
        visible = hasTriedFetching && weatherConditionsIsNull && !isLoading,
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
                    contentDescription = stringResource(R.string.no_weather_conditions_data_image),
                    modifier = Modifier
                        .size(64.dp)
                        .padding(bottom = 8.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                )
                Text(
                    text = stringResource(R.string.weather_conditions_no_data_available),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Composable
private fun WeatherConditionsDisplay(
    modifier: Modifier = Modifier,
    weatherConditions: WeatherConditions,
    unitSystemType: UnitSystemType,
    isLoading: Boolean,
    animDuration: Int = AnimationDurations.FADE_IN_OUT
) {
    val isPreview = LocalInspectionMode.current

    var isContentVisible by remember { mutableStateOf(isPreview) }

    LaunchedEffect(isLoading, weatherConditions) {
        isContentVisible = !isLoading
    }

    AnimatedVisibility(
        visible = isContentVisible,
        enter = fadeIn(animationSpec = tween(animDuration)),
        exit = fadeOut(animationSpec = tween(animDuration))
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                LocalObservationTimeDisplay(
                    localTime = weatherConditions.localObservationDateTime,
                    epochTime = weatherConditions.epochTime
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        TemperatureDisplay(
                            temperature = weatherConditions.temperature,
                            unitSystemType = unitSystemType
                        )
                        RealFeelTemperatureDisplay(
                            temperature = weatherConditions.realFeelTemperature,
                            unitSystemType = unitSystemType
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        TodayWeatherImage(weatherType = weatherConditions.weatherIcon)
                        GeneralDescriptionDisplay(description = weatherConditions.weatherText)
                        PrecipitationDisplay(precipitationType = weatherConditions.precipitationType)
                        HumidityDisplay(humidity = weatherConditions.relativeHumidity)
                        WindDisplay(
                            wind = weatherConditions.wind,
                            unitSystemType = unitSystemType
                        )
                        CloudCoverDisplay(cloudCover = weatherConditions.cloudCover)
                        PressureDisplay(
                            pressure = weatherConditions.pressure,
                            unitSystemType = unitSystemType
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TodayWeatherImage(
    modifier: Modifier = Modifier,
    weatherType: WeatherIconType
) {
    Image(
        modifier = modifier
            .size(50.dp)
            .clip(RoundedCornerShape(8.dp)),
        painter = painterResource(id = weatherType.resId),
        contentDescription = stringResource(id = weatherType.stringResId),
        colorFilter = ColorFilter.tint(weatherType.colorRes)
    )
}

@Composable
private fun LocalObservationTimeDisplay(
    modifier: Modifier = Modifier,
    localTime: LocalDateTime,
    epochTime: Int
) {
    val localTimeLabel = stringResource(id = R.string.local_time_label)
    val localTimeText = remember(epochTime) {
        localTime.formatTimeToString()
    }

    Text(
        modifier = modifier,
        text = "$localTimeLabel $localTimeText",
        style = MaterialTheme.typography.labelMedium,
    )
}

@Composable
private fun TemperatureDisplay(
    modifier: Modifier = Modifier,
    temperature: UnitType.Temperature?,
    unitSystemType: UnitSystemType
) {
    if (temperature != null) {
        val defaultFontColor = MaterialTheme.colorScheme.onBackground
        val (temperatureText, color) = remember(
            temperature.metric.value,
            temperature.imperial.value,
            unitSystemType
        ) {
            Pair(
                temperature.formatToString(unitSystemType, appendSpace = false),
                getFontColorWithMetricTemperature(
                    temperature = temperature.metric.value,
                    defaultColor = defaultFontColor
                )
            )
        }

        Text(
            modifier = modifier,
            text = temperatureText,
            style = MaterialTheme.typography.displayLarge,
            color = color
        )
    }
}

@Composable
private fun RealFeelTemperatureDisplay(
    modifier: Modifier = Modifier,
    temperature: UnitType.Temperature?,
    unitSystemType: UnitSystemType
) {
    if (temperature != null) {
        val realFeelLabel = stringResource(id = R.string.real_feel_label)
        val realFeelText = remember(
            temperature.metric.value,
            temperature.imperial.value,
            unitSystemType
        ) {
            temperature.formatToString(unitSystemType, appendSpace = false)
        }

        Text(
            modifier = modifier,
            text = "$realFeelLabel $realFeelText",
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun GeneralDescriptionDisplay(
    modifier: Modifier = Modifier,
    description: String
) {
    Text(modifier = modifier, text = description, style = MaterialTheme.typography.titleSmall)
}

@Composable
private fun PrecipitationDisplay(
    modifier: Modifier = Modifier,
    precipitationType: PrecipitationType,
) {
    val precipitationLabel = stringResource(id = R.string.precipitation_label)
    val precipitationTypeText = stringResource(id = precipitationType.resId)

    Text(
        modifier = modifier,
        text = "$precipitationLabel $precipitationTypeText",
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
private fun HumidityDisplay(
    modifier: Modifier = Modifier,
    humidity: Int?
) {
    if (humidity != null) {
        val humidityLabel = stringResource(id = R.string.humidity_label)

        Text(
            modifier = modifier,
            text = "$humidityLabel $humidity%",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun WindDisplay(
    modifier: Modifier = Modifier,
    wind: Wind?,
    unitSystemType: UnitSystemType
) {
    if (wind != null) {
        val windLabel = stringResource(id = R.string.wind_label)
        val windText = remember(
            wind.speed.metric.value,
            wind.speed.imperial.value,
            wind.direction.localized,
            unitSystemType
        ) {
            val speedText = wind.speed.formatToString(unitSystemType)
            val direction = wind.direction.localized
            "$speedText - $direction"
        }

        Text(
            modifier = modifier,
            text = "$windLabel $windText",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun CloudCoverDisplay(
    modifier: Modifier = Modifier,
    cloudCover: Int?
) {
    if (cloudCover != null) {
        val cloudCoverLabel = stringResource(id = R.string.cloud_cover_label)

        Text(
            modifier = modifier,
            text = "$cloudCoverLabel $cloudCover%",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun PressureDisplay(
    modifier: Modifier = Modifier,
    pressure: UnitType.Pressure?,
    unitSystemType: UnitSystemType
) {
    if (pressure != null) {
        val pressureLabel = stringResource(id = R.string.pressure_label)
        val pressureText = remember(
            pressure.metric.value,
            pressure.imperial.value,
            unitSystemType
        ) {
            pressure.formatToString(unitSystemType)
        }

        Text(
            modifier = modifier,
            text = "$pressureLabel $pressureText",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@PreviewLightDark
@Composable
private fun WeatherConditionsInfoPreview() {
    MrWeatherTheme {
        WeatherConditionsInfo(
            weatherConditionsState = conditionsPreview,
            isLoading = false,
        )
    }
}

@PreviewLightDark
@Composable
private fun WeatherConditionsInfoColdPreview() {
    MrWeatherTheme {
        WeatherConditionsInfo(
            weatherConditionsState = conditionsPreview.copy(
                temperature = UnitType.Temperature(
                    metricValue = 9.9,
                    imperialValue = 49.8
                ),
                realFeelTemperature = UnitType.Temperature(
                    metricValue = -2.7,
                    imperialValue = 27.1
                ),
            ),
            isLoading = false,
        )
    }
}

@PreviewLightDark
@Composable
private fun WeatherConditionsInfoWarmPreview() {
    MrWeatherTheme {
        WeatherConditionsInfo(
            weatherConditionsState = conditionsPreview.copy(
                temperature = UnitType.Temperature(
                    metricValue = 20.1,
                    imperialValue = 68.2
                ),
                realFeelTemperature = UnitType.Temperature(
                    metricValue = 19.9,
                    imperialValue = 67.8
                ),
            ),
            isLoading = false,
        )
    }
}

@PreviewLightDark
@Composable
private fun WeatherConditionsInfoLoadingPreview() {
    MrWeatherTheme {
        WeatherConditionsInfo(isLoading = true)
    }
}

@PreviewLightDark
@Composable
private fun WeatherConditionsInfoNoDataPreview() {
    MrWeatherTheme {
        WeatherConditionsInfo()
    }
}