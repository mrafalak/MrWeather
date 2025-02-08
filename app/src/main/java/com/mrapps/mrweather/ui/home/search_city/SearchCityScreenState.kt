package com.mrapps.mrweather.ui.home.search_city

import com.mrapps.mrweather.domain.model.SearchHistoryWithCity
import com.mrapps.mrweather.domain.model.location.City

data class SearchCityScreenState(
    val cities: List<City> = emptyList(),
    val searchHistoryWithCities: List<SearchHistoryWithCity> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = false,
    val error: Throwable? = null,
) {
    fun shouldShowHistory(): Boolean {
        return cities.isEmpty() && searchHistoryWithCities.isNotEmpty() && query.isBlank()
    }
}