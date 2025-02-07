package com.mrapps.mrweather.ui.home.search

import com.mrapps.mrweather.domain.model.location.City

data class SearchScreenState(
    val cities: List<City> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = false,
    val error: Throwable? = null,
)