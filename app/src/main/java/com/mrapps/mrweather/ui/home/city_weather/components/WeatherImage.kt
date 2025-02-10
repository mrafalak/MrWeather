package com.mrapps.mrweather.ui.home.city_weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mrapps.mrweather.domain.model.weather_condition.WeatherIconType

@Composable
fun WeatherImage(
    modifier: Modifier = Modifier,
    weatherType: WeatherIconType
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = weatherType.resId),
            contentDescription = stringResource(id = weatherType.stringResId),
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(8.dp)),
            colorFilter = ColorFilter.tint(weatherType.colorRes)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = weatherType.stringResId),
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}