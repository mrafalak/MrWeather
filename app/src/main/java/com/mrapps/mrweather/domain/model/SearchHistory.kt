package com.mrapps.mrweather.domain.model

import java.time.LocalDateTime

data class SearchHistory(
    val cityId: String,
    val searchTime: LocalDateTime
)