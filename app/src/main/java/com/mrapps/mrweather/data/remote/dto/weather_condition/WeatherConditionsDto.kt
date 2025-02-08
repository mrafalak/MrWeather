package com.mrapps.mrweather.data.remote.dto.weather_condition

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.weather_condition.WeatherConditions
import com.mrapps.mrweather.domain.model.weather_condition.PressureTendency
import com.mrapps.mrweather.domain.util.convertStringToLocalDateTime

data class WeatherConditionsDto(
    @SerializedName("WeatherText") val weatherText: String,
    @SerializedName("WeatherIcon") val weatherIcon: Int,
    @SerializedName("Temperature") val temperature: UnitDto,
    @SerializedName("RealFeelTemperature") val realFeelTemperature: UnitDto,
    @SerializedName("RealFeelTemperatureShade") val realFeelTemperatureShade: UnitDto,
    @SerializedName("HasPrecipitation") val hasPrecipitation: Boolean,
    @SerializedName("Wind") val wind: WindDto,
    @SerializedName("CloudCover") val cloudCover: Int,
    @SerializedName("Pressure") val pressure: UnitDto,
    @SerializedName("PressureTendency") val pressureTendency: PressureTendencyDto,
    @SerializedName("LocalObservationDateTime") val localObservationDateTime: String,
    @SerializedName("EpochTime") val epochTime: Int
) {
    fun toWeatherConditions(): WeatherConditions = WeatherConditions(
        weatherText = this.weatherText,
        weatherIcon = this.weatherIcon,
        temperature = this.temperature.toUnit(),
        realFeelTemperature = this.realFeelTemperature.toUnit(),
        realFeelTemperatureShade = this.realFeelTemperatureShade.toUnit(),
        hasPrecipitation = this.hasPrecipitation,
        wind = this.wind.toWind(),
        cloudCover = this.cloudCover,
        pressure = this.pressure.toUnit(),
        pressureTendency = PressureTendency.fromValue(this.pressureTendency.code),
        localObservationDateTime = convertStringToLocalDateTime(this.localObservationDateTime),
        epochTime = this.epochTime
    )
}