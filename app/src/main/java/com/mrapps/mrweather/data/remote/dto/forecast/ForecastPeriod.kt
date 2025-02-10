package com.mrapps.mrweather.data.remote.dto.forecast

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.data.remote.dto.unit.UnitIntDto
import com.mrapps.mrweather.data.remote.dto.weather_condition.WindDto
import com.mrapps.mrweather.domain.model.forecast.ForecastPeriod
import com.mrapps.mrweather.domain.model.weather_condition.WeatherIconType

data class ForecastPeriodDto(
    @SerializedName("Icon") val icon: Int,
    @SerializedName("IconPhrase") val iconPhrase: String,
    @SerializedName("ShortPhrase") val shortPhrase: String,
    @SerializedName("LongPhrase") val longPhrase: String,
    @SerializedName("CloudCover") val cloudCover: Int,
    @SerializedName("RelativeHumidity") val relativeHumidity: RelativeHumidityDto,
    @SerializedName("HasPrecipitation") val hasPrecipitation: Boolean,
    @SerializedName("PrecipitationProbability") val precipitationProbability: Int,
    @SerializedName("HoursOfPrecipitation") val hoursOfPrecipitation: Int,
    @SerializedName("Rain") val rain: UnitIntDto,
    @SerializedName("RainProbability") val rainProbability: Int,
    @SerializedName("HoursOfRain") val hoursOfRain: Int,
    @SerializedName("Snow") val snow: UnitIntDto,
    @SerializedName("SnowProbability") val snowProbability: Int,
    @SerializedName("HoursOfSnow") val hoursOfSnow: Int,
    @SerializedName("Ice") val ice: UnitIntDto,
    @SerializedName("IceProbability") val iceProbability: Int,
    @SerializedName("HoursOfIce") val hoursOfIce: Int,
    @SerializedName("Wind") val wind: WindDto
) {
    fun toForecastPeriod(metric: Boolean): ForecastPeriod {
        return ForecastPeriod(
            icon = WeatherIconType.fromTypeId(icon),
            shortPhrase = shortPhrase,
            cloudCover = cloudCover,
            relativeHumidity = relativeHumidity.toRelativeHumidity(),
            hasPrecipitation = hasPrecipitation,
            precipitationProbability = precipitationProbability,
            hoursOfPrecipitation = hoursOfPrecipitation,
            rain = rain.toSmallLength(metric),
            rainProbability = rainProbability,
            hoursOfRain = hoursOfRain,
            snow = snow.toLength(metric),
            snowProbability = snowProbability,
            hoursOfSnow = hoursOfSnow,
            ice = ice.toSmallLength(metric),
            iceProbability = iceProbability,
            hoursOfIce = hoursOfIce,
            wind = wind.toWind(metric)
        )
    }
}