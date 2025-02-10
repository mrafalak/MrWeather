package com.mrapps.mrweather.data.remote.dto.forecast

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.forecast.DailyForecast
import com.mrapps.mrweather.domain.util.convertStringToOffsetDateTime

data class DailyForecastDto(
    @SerializedName("Date") val date: String,
    @SerializedName("EpochDate") val epochDate: Int,
    @SerializedName("Temperature") val temperatures: TemperaturesRangeDto,
    @SerializedName("RealFeelTemperature") val realFeelTemperature: TemperaturesRangeDto,
    @SerializedName("RealFeelTemperatureShade") val realFeelTemperatureShade: TemperaturesRangeDto,
    @SerializedName("Day") val day: ForecastPeriodDto,
    @SerializedName("Night") val night: ForecastPeriodDto,
    @SerializedName("Sun") val sun: SunDto,
    @SerializedName("Moon") val moon: MoonDto
) {
    fun toDailyForecast(metric: Boolean): DailyForecast = DailyForecast(
        date = convertStringToOffsetDateTime(this.date, this.epochDate),
        epochDate = this.epochDate,
        temperatures = this.temperatures.toTemperaturesRange(metric),
        realFeelTemperature = this.realFeelTemperature.toTemperaturesRange(metric),
        realFeelTemperatureShade = this.realFeelTemperatureShade.toTemperaturesRange(metric),
        day = this.day.toForecastPeriod(metric),
        night = this.night.toForecastPeriod(metric),
        sun = this.sun.toSun(),
        moon = this.moon.toMoon()
    )
}