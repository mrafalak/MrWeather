package com.mrapps.mrweather.domain.model.forecast

import com.mrapps.mrweather.domain.model.units.UnitType
import com.mrapps.mrweather.domain.model.weather_condition.WeatherIconType
import com.mrapps.mrweather.domain.model.weather_condition.Wind

data class ForecastPeriod(
    val icon: WeatherIconType,
    val shortPhrase: String,
    val cloudCover: Int,
    val relativeHumidity: RelativeHumidity,
    val hasPrecipitation: Boolean,
    val precipitationProbability: Int,
    val hoursOfPrecipitation: Int,
    val rain: UnitType.SmallLength,
    val rainProbability: Int,
    val hoursOfRain: Int,
    val snow: UnitType.Length,
    val snowProbability: Int,
    val hoursOfSnow: Int,
    val ice: UnitType.SmallLength,
    val iceProbability: Int,
    val hoursOfIce: Int,
    val wind: Wind
)