package com.mrapps.mrweather.data.remote.fake

import com.mrapps.mrweather.data.remote.WeatherApi
import com.mrapps.mrweather.data.remote.dto.forecast.ForecastDto
import com.mrapps.mrweather.data.remote.dto.location.CityDto
import com.mrapps.mrweather.data.remote.dto.weather_condition.WeatherConditionsDto
import com.mrapps.mrweather.data.remote.util.LanguageEnum
import com.mrapps.mrweather.data.remote.util.SearchCityAlias
import kotlinx.coroutines.delay

class FakeWeatherApi : WeatherApi {
    override suspend fun searchCities(
        query: String,
        language: LanguageEnum,
        alias: SearchCityAlias
    ): List<CityDto> {
        delay(1000)
        return listOf(FakeDtoObjects.Cities.cityDto)
    }

    override suspend fun fetchCurrentConditions(
        locationKey: String,
        language: LanguageEnum,
        details: Boolean
    ): List<WeatherConditionsDto> {
        delay(2000)
        return listOf(FakeDtoObjects.Conditions.conditionsDto)
    }

    override suspend fun fetchFiveDaysForecast(
        locationKey: String,
        language: LanguageEnum,
        details: Boolean,
        metric: Boolean
    ): ForecastDto {
        delay(2000)
        return ForecastDto(dailyForecasts = FakeDtoObjects.Forecast.fiveDaysMetricForecastDto)
    }
}