package com.mrapps.mrweather.data.remote.dto.weather_condition

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.units.UnitType
import com.mrapps.mrweather.domain.model.weather_condition.PrecipitationType
import com.mrapps.mrweather.domain.model.weather_condition.WeatherConditions
import com.mrapps.mrweather.domain.model.weather_condition.PressureTendency
import com.mrapps.mrweather.domain.util.convertStringToOffsetDateTime
import com.mrapps.mrweather.ui.util.roundToSingleDecimal

data class WeatherConditionsDto(
    @SerializedName("WeatherText") val weatherText: String,
    @SerializedName("WeatherIcon") val weatherIcon: Int,
    @SerializedName("Temperature") val temperature: UnitDto,
    @SerializedName("RealFeelTemperature") val realFeelTemperature: UnitDto,
    @SerializedName("RealFeelTemperatureShade") val realFeelTemperatureShade: UnitDto,
    @SerializedName("RelativeHumidity") val relativeHumidity: Int,
    @SerializedName("HasPrecipitation") val hasPrecipitation: Boolean,
    @SerializedName("PrecipitationType") val precipitationType: String?,
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
        temperature = UnitType.Temperature(
            roundToSingleDecimal(this.temperature.metric.value),
            roundToSingleDecimal(this.temperature.imperial.value)
        ),
        realFeelTemperature = UnitType.Temperature(
            roundToSingleDecimal(this.realFeelTemperature.metric.value),
            roundToSingleDecimal(this.realFeelTemperature.imperial.value)
        ),
        realFeelTemperatureShade = UnitType.Temperature(
            roundToSingleDecimal(this.realFeelTemperatureShade.metric.value),
            roundToSingleDecimal(this.realFeelTemperatureShade.imperial.value)
        ),
        relativeHumidity = this.relativeHumidity,
        hasPrecipitation = this.hasPrecipitation,
        precipitationType = PrecipitationType.fromValue(this.precipitationType),
        wind = this.wind.toWind(),
        cloudCover = this.cloudCover,
        pressure = UnitType.Pressure(
            this.pressure.metric.value,
            this.pressure.imperial.value
        ),
        pressureTendency = PressureTendency.fromValue(this.pressureTendency.code),
        localObservationDateTime = convertStringToOffsetDateTime(
            this.localObservationDateTime,
            this.epochTime
        ),
        epochTime = this.epochTime
    )
}