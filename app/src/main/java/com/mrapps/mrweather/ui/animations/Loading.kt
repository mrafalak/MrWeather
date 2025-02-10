package com.mrapps.mrweather.ui.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity

fun Modifier.shimmerEffect(
    color: Color,
    animationDuration: Int = AnimationDurations.SHIMMER_EFFECT,
    animationDelay: Int = 0
): Modifier = composed {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenWidthPx =
        with(density) { configuration.screenWidthDp.dp.toPx() }

    val shimmerColors = remember(color) {
        listOf(
            color.copy(alpha = 0.01f),
            color.copy(alpha = 0.02f),
            color.copy(alpha = 0.03f),
            color.copy(alpha = 0.04f),
            color.copy(alpha = 0.05f),
            color.copy(alpha = 0.06f),
            color.copy(alpha = 0.05f),
            color.copy(alpha = 0.04f),
            color.copy(alpha = 0.03f),
            color.copy(alpha = 0.02f),
            color.copy(alpha = 0.01f),
        )
    }

    val transition = rememberInfiniteTransition(label = "Shimmer Transition")
    val translateAnim = transition.animateFloat(
        initialValue = -200f,
        targetValue = screenWidthPx + 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                delayMillis = animationDelay,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = "Shimmer Transition"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim.value - 200f, translateAnim.value / 2),
        end = Offset(translateAnim.value, translateAnim.value + 200f)
    )

    this.background(brush)
}