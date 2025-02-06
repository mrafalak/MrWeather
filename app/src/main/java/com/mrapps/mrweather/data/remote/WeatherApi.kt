package com.mrapps.mrweather.data.remote

import com.mrapps.mrweather.data.remote.dto.CityDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @RequiresAuth
    @GET("locations/v1/cities/search")
    suspend fun searchCities(
        @Query("q") query: String
    ): List<CityDto>
}