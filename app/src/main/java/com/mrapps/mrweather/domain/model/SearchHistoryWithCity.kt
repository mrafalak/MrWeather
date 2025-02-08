package com.mrapps.mrweather.domain.model

import com.mrapps.mrweather.domain.model.location.City

data class SearchHistoryWithCity(
    val searchHistory: SearchHistory,
    val city: City
)