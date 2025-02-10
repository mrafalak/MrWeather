package com.mrapps.mrweather.domain.model.forecast

import java.time.LocalDateTime

data class DailyForecast(
    val date: LocalDateTime,
    val epochDate: Int,
    val temperatures: TemperaturesRange?,
    val realFeelTemperature: TemperaturesRange?,
    val realFeelTemperatureShade: TemperaturesRange?,
    val day: ForecastPeriod,
    val night: ForecastPeriod,
    val sun: Sun,
    val moon: Moon,
)