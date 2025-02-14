package com.mrapps.mrweather.ui.home.city_weather.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mrapps.mrweather.ui.animations.AnimationDurations
import com.mrapps.mrweather.ui.animations.shimmerEffect
import com.mrapps.mrweather.ui.theme.ThemeWithSurface
import com.mrapps.mrweather.ui.util.preview.ThemePreview

const val LOADING_BOX_ANIM_LABEL = "LoadingBoxVisibility"

@Composable
fun LoadingBox(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    animDuration: Int = AnimationDurations.FADE_IN_OUT,
    animLabel: String = LOADING_BOX_ANIM_LABEL
) {
    AnimatedVisibility(
        visible = isLoading,
        enter = fadeIn(animationSpec = tween(animDuration)),
        exit = fadeOut(animationSpec = tween(animDuration)),
        label = animLabel
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

@ThemePreview
@Composable
private fun LoadingBoxPreview() {
    ThemeWithSurface(modifier = Modifier.size(200.dp)) {
        LoadingBox(isLoading = true)
    }
}