package com.mrapps.mrweather.ui.home.city_weather


sealed class CityWeatherScreenAction {
    data class FetchCityData(val cityId: String) : CityWeatherScreenAction()
    data class FetchCurrentConditions(val cityId: String) : CityWeatherScreenAction()
    data object ClearError : CityWeatherScreenAction()
    data object NavigateBack : CityWeatherScreenAction()
}