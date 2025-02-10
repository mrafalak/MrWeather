package com.mrapps.mrweather.data.remote.dto.weather_condition

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.data.remote.dto.unit.UnitsDto
import com.mrapps.mrweather.data.remote.dto.unit.calculatePressure
import com.mrapps.mrweather.data.remote.dto.unit.calculateTemperature
import com.mrapps.mrweather.domain.model.weather_condition.PrecipitationType
import com.mrapps.mrweather.domain.model.weather_condition.WeatherConditions
import com.mrapps.mrweather.domain.model.weather_condition.PressureTendency
import com.mrapps.mrweather.domain.model.weather_condition.WeatherIconType
import com.mrapps.mrweather.domain.util.convertStringToOffsetDateTime

data class WeatherConditionsDto(
    @SerializedName("WeatherText") val weatherText: String,
    @SerializedName("WeatherIcon") val weatherIcon: Int?,
    @SerializedName("Temperature") val temperature: UnitsDto,
    @SerializedName("RealFeelTemperature") val realFeelTemperature: UnitsDto,
    @SerializedName("RealFeelTemperatureShade") val realFeelTemperatureShade: UnitsDto,
    @SerializedName("RelativeHumidity") val relativeHumidity: Int?,
    @SerializedName("HasPrecipitation") val hasPrecipitation: Boolean,
    @SerializedName("PrecipitationType") val precipitationType: String?,
    @SerializedName("Wind") val wind: WindDto,
    @SerializedName("CloudCover") val cloudCover: Int?,
    @SerializedName("Pressure") val pressure: UnitsDto,
    @SerializedName("PressureTendency") val pressureTendency: PressureTendencyDto,
    @SerializedName("LocalObservationDateTime") val localObservationDateTime: String,
    @SerializedName("EpochTime") val epochTime: Int
) {
    fun toWeatherConditions(metric: Boolean = true): WeatherConditions {
        val temperature = calculateTemperature(this.temperature)
        val realFeelTemperature = calculateTemperature(this.realFeelTemperature)
        val realFeelTemperatureShade = calculateTemperature(this.realFeelTemperatureShade)
        val pressure = calculatePressure(this.pressure)

        return WeatherConditions(
            weatherText = this.weatherText,
            weatherIcon = WeatherIconType.fromTypeId(this.weatherIcon),
            temperature = temperature,
            realFeelTemperature = realFeelTemperature,
            realFeelTemperatureShade = realFeelTemperatureShade,
            relativeHumidity = this.relativeHumidity,
            hasPrecipitation = this.hasPrecipitation,
            precipitationType = PrecipitationType.fromValue(this.precipitationType),
            wind = this.wind.toWind(metric),
            cloudCover = this.cloudCover,
            pressure = pressure,
            pressureTendency = PressureTendency.fromValue(this.pressureTendency.code),
            localObservationDateTime = convertStringToOffsetDateTime(
                this.localObservationDateTime,
                this.epochTime
            ),
            epochTime = this.epochTime
        )
    }
}