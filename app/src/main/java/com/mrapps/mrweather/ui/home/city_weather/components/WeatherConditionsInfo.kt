package com.mrapps.mrweather.ui.home.city_weather.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mrapps.mrweather.R
import com.mrapps.mrweather.domain.model.units.Imperial
import com.mrapps.mrweather.domain.model.units.Metric
import com.mrapps.mrweather.domain.model.units.Unit
import com.mrapps.mrweather.domain.model.weather_condition.Direction
import com.mrapps.mrweather.domain.model.weather_condition.PressureTendency
import com.mrapps.mrweather.domain.model.weather_condition.WeatherConditions
import com.mrapps.mrweather.domain.model.weather_condition.Wind
import com.mrapps.mrweather.ui.theme.MrWeatherTheme
import java.time.LocalDateTime

@Composable
fun WeatherConditionsInfo(
    modifier: Modifier = Modifier,
    weatherConditions: WeatherConditions? = null,
    isLoading: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (weatherConditions == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(R.string.weather_conditions_no_data_available),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
        } else {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                val temperatureLabel = stringResource(id = R.string.temperature_label)
                val realFeelLabel = stringResource(id = R.string.real_feel_label)
                val windLabel = stringResource(id = R.string.wind_label)
                val pressureLabel = stringResource(id = R.string.pressure_label)
                val cloudCoverLabel = stringResource(id = R.string.cloud_cover_label)

                val metricTemperature = weatherConditions.temperature.metric.value
                val metricTemperatureUnit = weatherConditions.temperature.metric.unit.unit
                val imperialTemperature = weatherConditions.temperature.imperial.value
                val imperialUnit = weatherConditions.temperature.imperial.unit.unit

                val realFeelMetricTemperature = weatherConditions.realFeelTemperature.metric.value
                val realFeelImperialTemperature =
                    weatherConditions.realFeelTemperature.imperial.value

                val windSpeedMetric = weatherConditions.wind.speed.metric.value
                val windSpeedMetricUnit = weatherConditions.wind.speed.metric.unit.unit
                val windSpeedImperial = weatherConditions.wind.speed.imperial.value
                val windSpeedImperialUnit = weatherConditions.wind.speed.imperial.unit.unit
                val windDirection = weatherConditions.wind.direction.localized

                val pressureMetric = weatherConditions.pressure.metric.value
                val pressureMetricUnit = weatherConditions.pressure.metric.unit.unit
                val pressureImperial = weatherConditions.pressure.imperial.value
                val pressureImperialUnit = weatherConditions.pressure.imperial.unit.unit

                val cloudCover = weatherConditions.cloudCover

                Text(text = "$temperatureLabel $metricTemperature°$metricTemperatureUnit / $imperialTemperature°$imperialUnit")
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "$realFeelLabel $realFeelMetricTemperature°$metricTemperatureUnit / $realFeelImperialTemperature°$imperialUnit")
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "$windLabel $windSpeedMetric $windSpeedMetricUnit / $windSpeedImperial $windSpeedImperialUnit - $windDirection")
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "$pressureLabel $pressureMetric $pressureMetricUnit / $pressureImperial $pressureImperialUnit")
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "$cloudCoverLabel $cloudCover%")
            }
        }
    }
}

@PreviewLightDark
@Composable
fun WeatherConditionsInfoPreview() {
    MrWeatherTheme {
        WeatherConditionsInfo(weatherConditions = conditionsPreview, isLoading = false)
    }
}

@PreviewLightDark
@Composable
fun WeatherConditionsInfoLoadingPreview() {
    MrWeatherTheme {
        WeatherConditionsInfo(isLoading = true)
    }
}

@PreviewLightDark
@Composable
fun WeatherConditionsInfoNoDataPreview() {
    MrWeatherTheme {
        WeatherConditionsInfo()
    }
}

val conditionsPreview = WeatherConditions(
    weatherText = "Partly cloudy",
    weatherIcon = 3,
    temperature = Unit(
        imperial = Imperial.Temperature(68.0),
        metric = Metric.Temperature(20.0)
    ),
    realFeelTemperature = Unit(
        imperial = Imperial.Temperature(72.0),
        metric = Metric.Temperature(22.0)
    ),
    realFeelTemperatureShade = Unit(
        imperial = Imperial.Temperature(66.0),
        metric = Metric.Temperature(19.0)
    ),
    hasPrecipitation = false,
    wind = Wind(
        direction = Direction(
            degrees = 270,
            english = "W",
            localized = "Z"
        ),
        speed = Unit(
            imperial = Imperial.SpeedMIH(10.0),
            metric = Metric.SpeedKMH(16.1)
        )
    ),
    cloudCover = 60,
    pressure = Unit(
        imperial = Imperial.Pressure(30.1),
        metric = Metric.Pressure(1018.0)
    ),
    pressureTendency = PressureTendency.STEADY,
    localObservationDateTime = LocalDateTime.parse("2023-02-07T14:00:00"),
    epochTime = 1675772400
)
