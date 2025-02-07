package com.mrapps.mrweather.data.remote

import com.mrapps.mrweather.data.remote.dto.location.CityDto
import com.mrapps.mrweather.data.remote.dto.weather_condition.WeatherConditionsDto
import com.mrapps.mrweather.data.remote.util.LanguageEnum
import com.mrapps.mrweather.data.remote.util.SearchCityAlias
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_VERSION = "v1"
private val DEFAULT_LANGUAGE = LanguageEnum.PL

interface WeatherApi {

    @RequiresAuth
    @GET("locations/${API_VERSION}/cities/search")
    suspend fun searchCities(
        @Query("q") query: String,
        @Query("language") language: LanguageEnum = DEFAULT_LANGUAGE,
        @Query("alias") alias: SearchCityAlias = SearchCityAlias.ALWAYS,
    ): List<CityDto>

    @RequiresAuth
    @GET("currentconditions/${API_VERSION}/{locationKey}")
    suspend fun fetchCurrentConditions(
        @Path("locationKey") locationKey: String,
        @Query("language") language: LanguageEnum = DEFAULT_LANGUAGE,
        @Query("details") details: Boolean = true,
    ): List<WeatherConditionsDto>
}