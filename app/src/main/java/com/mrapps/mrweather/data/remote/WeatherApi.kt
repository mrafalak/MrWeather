package com.mrapps.mrweather.data.remote

import com.mrapps.mrweather.data.remote.dto.CityDto
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_VERSION = "v1"

interface WeatherApi {

    @RequiresAuth
    @GET("locations/${API_VERSION}/cities/search")
    suspend fun searchCities(
        @Query("q") query: String
    ): List<CityDto>
}