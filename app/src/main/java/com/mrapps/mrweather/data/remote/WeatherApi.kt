package com.mrapps.mrweather.data.remote

import com.mrapps.mrweather.data.remote.dto.CityDto
import com.mrapps.mrweather.data.remote.util.LanguageEnum
import com.mrapps.mrweather.data.remote.util.SearchCityAlias
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_VERSION = "v1"

interface WeatherApi {

    @RequiresAuth
    @GET("locations/${API_VERSION}/cities/search")
    suspend fun searchCities(
        @Query("q") query: String,
        @Query("language") language: LanguageEnum = LanguageEnum.PL,
        @Query("alias") alias: SearchCityAlias = SearchCityAlias.ALWAYS,
    ): List<CityDto>
}