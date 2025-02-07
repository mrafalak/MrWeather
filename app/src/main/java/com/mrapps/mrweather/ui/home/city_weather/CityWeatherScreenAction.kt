package com.mrapps.mrweather.ui.home.city_weather


sealed class CityWeatherScreenAction {
    data class FetchCurrentConditions(val cityId: String) : CityWeatherScreenAction()
    data object NavigateBack : CityWeatherScreenAction()
}