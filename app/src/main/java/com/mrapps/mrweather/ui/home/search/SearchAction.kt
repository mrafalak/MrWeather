package com.mrapps.mrweather.ui.home.search

import com.mrapps.mrweather.domain.model.City

sealed class SearchAction {
    data class QueryChanged(val query: String) : SearchAction()
    data object SearchClicked : SearchAction()
    data class CityClicked(val city: City) : SearchAction()
    data object ClearError : SearchAction()
    data object NavigateBack : SearchAction()
}