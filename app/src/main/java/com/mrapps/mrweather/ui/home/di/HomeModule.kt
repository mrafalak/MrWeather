package com.mrapps.mrweather.ui.home.di

import com.mrapps.mrweather.ui.home.city_weather.CityWeatherViewModel
import com.mrapps.mrweather.ui.home.search_city.SearchCityViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { SearchCityViewModel(get()) }
    viewModel { CityWeatherViewModel(get()) }
}