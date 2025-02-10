package com.mrapps.mrweather.data.remote.dto.forecast

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.forecast.DailyForecast
import com.mrapps.mrweather.domain.util.convertStringToOffsetDateTime

data class DailyForecastDto(
    @SerializedName("Date") val date: String,
    @SerializedName("EpochDate") val epochDate: Int,
    @SerializedName("Temperature") val temperatures: TemperaturesRangeDto?,
    @SerializedName("RealFeelTemperature") val realFeelTemperature: TemperaturesRangeDto?,
    @SerializedName("RealFeelTemperatureShade") val realFeelTemperatureShade: TemperaturesRangeDto?,
    @SerializedName("Day") val day: ForecastPeriodDto,
    @SerializedName("Night") val night: ForecastPeriodDto,
    @SerializedName("Sun") val sun: SunDto,
    @SerializedName("Moon") val moon: MoonDto
) {
    fun toDailyForecast(metric: Boolean): DailyForecast {
        val temperatures = if (this.temperatures == null) {
            null
        } else {
            this.temperatures.toTemperaturesRange(metric)
        }
        val realFeelTemperature = if (this.realFeelTemperature == null) {
            null
        } else {
            this.realFeelTemperature.toTemperaturesRange(metric)
        }
        val realFeelTemperatureShade = if (this.realFeelTemperatureShade == null) {
            null
        } else {
            this.realFeelTemperatureShade.toTemperaturesRange(metric)
        }

        return DailyForecast(
            date = convertStringToOffsetDateTime(this.date, this.epochDate),
            epochDate = this.epochDate,
            temperatures = temperatures,
            realFeelTemperature = realFeelTemperature,
            realFeelTemperatureShade = realFeelTemperatureShade,
            day = this.day.toForecastPeriod(metric),
            night = this.night.toForecastPeriod(metric),
            sun = this.sun.toSun(),
            moon = this.moon.toMoon()
        )
    }
}