package com.mrapps.mrweather.ui.home.city_weather.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mrapps.mrweather.R
import com.mrapps.mrweather.ui.animations.AnimationDurations
import com.mrapps.mrweather.ui.theme.ThemeWithSurface
import com.mrapps.mrweather.ui.util.preview.ThemePreview

@Composable
fun DataNotFoundBox(
    modifier: Modifier = Modifier,
    dataIsNull: Boolean,
    isLoading: Boolean,
    infoResId: Int,
    animDuration: Int = AnimationDurations.FADE_IN_OUT
) {
    val isPreview = LocalInspectionMode.current
    var hasTriedFetching by rememberSaveable { mutableStateOf(isPreview) }

    LaunchedEffect(isLoading) {
        if (isLoading) hasTriedFetching = true
    }

    AnimatedVisibility(
        visible = hasTriedFetching && dataIsNull && !isLoading,
        enter = fadeIn(animationSpec = tween(animDuration)),
        exit = fadeOut(animationSpec = tween(animDuration)),
        label = stringResource(infoResId)
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    imageVector = Icons.Default.Warning,
                    contentDescription = stringResource(infoResId),
                    modifier = Modifier
                        .size(64.dp)
                        .padding(bottom = 8.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                )
                Text(
                    text = stringResource(infoResId),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@ThemePreview
@Composable
fun DataNotFoundPreview(modifier: Modifier = Modifier) {
    ThemeWithSurface {
        DataNotFoundBox(
            modifier = Modifier.size(300.dp),
            dataIsNull = true,
            isLoading = false,
            infoResId = R.string.forecast_no_data_available,
        )
    }
}