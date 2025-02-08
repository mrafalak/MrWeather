package com.mrapps.mrweather.ui.home.search_city

import com.mrapps.mrweather.domain.model.location.City

sealed class SearchCityScreenAction {
    data class QueryChanged(val query: String) : SearchCityScreenAction()
    data object SearchClicked : SearchCityScreenAction()
    data class CityClicked(val city: City) : SearchCityScreenAction()
    data object ClearError : SearchCityScreenAction()
}