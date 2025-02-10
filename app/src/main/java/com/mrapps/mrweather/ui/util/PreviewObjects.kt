package com.mrapps.mrweather.ui.util

import com.mrapps.mrweather.data.remote.dto.forecast.DailyForecastDto
import com.mrapps.mrweather.data.remote.dto.forecast.ForecastPeriodDto
import com.mrapps.mrweather.data.remote.dto.forecast.MoonDto
import com.mrapps.mrweather.data.remote.dto.forecast.RelativeHumidityDto
import com.mrapps.mrweather.data.remote.dto.forecast.SunDto
import com.mrapps.mrweather.data.remote.dto.forecast.TemperaturesRangeDto
import com.mrapps.mrweather.data.remote.dto.unit.UnitDoubleDto
import com.mrapps.mrweather.data.remote.dto.weather_condition.DirectionDto
import com.mrapps.mrweather.data.remote.dto.weather_condition.WindDto
import com.mrapps.mrweather.domain.model.units.UnitType
import com.mrapps.mrweather.domain.model.weather_condition.Direction
import com.mrapps.mrweather.domain.model.weather_condition.PrecipitationType
import com.mrapps.mrweather.domain.model.weather_condition.PressureTendency
import com.mrapps.mrweather.domain.model.weather_condition.WeatherConditions
import com.mrapps.mrweather.domain.model.weather_condition.WeatherIconType
import com.mrapps.mrweather.domain.model.weather_condition.Wind
import java.time.LocalDateTime

object PreviewObjects {
    val baseForecastDto = DailyForecastDto(
        date = "2025-02-11T07:00:00+01:00",
        epochDate = 1739253600,
        temperatures = TemperaturesRangeDto(
            maximum = UnitDoubleDto(unit = "°C", unitType = 17, value = 10.1),
            minimum = UnitDoubleDto(unit = "°C", unitType = 17, value = -1.1)
        ),
        realFeelTemperature = TemperaturesRangeDto(
            maximum = UnitDoubleDto(unit = "°C", unitType = 17, value = 4.99),
            minimum = UnitDoubleDto(unit = "°C", unitType = 17, value = -3.22)
        ),
        realFeelTemperatureShade = TemperaturesRangeDto(
            maximum = UnitDoubleDto(unit = "°C", unitType = 17, value = 3.0),
            minimum = UnitDoubleDto(unit = "°C", unitType = 17, value = -4.0)
        ),
        day = ForecastPeriodDto(
            icon = 13,
            iconPhrase = "Częściowe zachmurzenie",
            shortPhrase = "Chłodno i słonecznie",
            longPhrase = "Słoneczny dzień z okresowymi chmurami, chłodno",
            cloudCover = 30,
            relativeHumidity = RelativeHumidityDto(average = 70, maximum = 85, minimum = 55),
            hasPrecipitation = false,
            precipitationProbability = 10,
            hoursOfPrecipitation = 0,
            rain = UnitDoubleDto(unit = "mm", unitType = 20, value = 0.0),
            rainProbability = 5,
            hoursOfRain = 0,
            snow = UnitDoubleDto(unit = "cm", unitType = 19, value = 0.0),
            snowProbability = 20,
            hoursOfSnow = 0,
            ice = UnitDoubleDto(unit = "mm", unitType = 20, value = 0.0),
            iceProbability = 5,
            hoursOfIce = 0,
            wind = WindDto(
                direction = DirectionDto(
                    degrees = 180,
                    english = "South",
                    localized = "Południowy"
                ),
                speed = UnitDoubleDto(
                    unit = "km/h", unitType = 7, value = 15.0
                )
            )
        ),
        night = ForecastPeriodDto(
            icon = 33,
            iconPhrase = "Przeważnie bezchmurnie",
            shortPhrase = "Zimno i przejrzyście",
            longPhrase = "Bezchmurna noc, zimno",
            cloudCover = 10,
            relativeHumidity = RelativeHumidityDto(average = 80, maximum = 90, minimum = 70),
            hasPrecipitation = false,
            precipitationProbability = 5,
            hoursOfPrecipitation = 0,
            rain = UnitDoubleDto(unit = "mm", unitType = 20, value = 0.0),
            rainProbability = 2,
            hoursOfRain = 0,
            snow = UnitDoubleDto(unit = "cm", unitType = 19, value = 0.0),
            snowProbability = 15,
            hoursOfSnow = 0,
            ice = UnitDoubleDto(unit = "mm", unitType = 20, value = 0.0),
            iceProbability = 3,
            hoursOfIce = 0,
            wind = WindDto(
                direction = DirectionDto(degrees = 270, english = "West", localized = "Zachodni"),
                speed = UnitDoubleDto(
                    unit = "km/h", unitType = 7, value = 15.0
                )
            )
        ),
        sun = SunDto(epochRise = 1733750400, epochSet = 1733793600, rise = "07:30", set = "16:30"),
        moon = MoonDto(
            epochRise = 1733790000,
            epochSet = 1733828400,
            phase = "Ubywający Księżyc",
            rise = "16:00",
            set = "02:00",
            age = 10
        )
    )
    val baseForecast = baseForecastDto.toDailyForecast(true)

    val fiveDaysForecast = listOf(
        baseForecast,
        baseForecast,
        baseForecast,
        baseForecast,
        baseForecast,
    )

    val conditionsPreview = WeatherConditions(
        weatherText = "Zachmurzenie",
        weatherIcon = WeatherIconType.CLOUDY,
        temperature = UnitType.Temperature(
            metricValue = 11.0,
            imperialValue = 51.8
        ),
        realFeelTemperature = UnitType.Temperature(
            metricValue = -2.7,
            imperialValue = 27.1
        ),
        realFeelTemperatureShade = UnitType.Temperature(
            metricValue = -5.0,
            imperialValue = 23.0
        ),
        relativeHumidity = 82,
        hasPrecipitation = false,
        precipitationType = PrecipitationType.NONE,
        wind = Wind(
            direction = Direction(
                degrees = 250,
                english = "W",
                localized = "Z"
            ),
            speed = UnitType.Speed(
                kmh = 18.0,
                mph = 11.2
            )
        ),
        cloudCover = 60,
        pressure = UnitType.Pressure(
            mb = 1018.0,
            inHg = 30.0
        ),
        pressureTendency = PressureTendency.STEADY,
        localObservationDateTime = LocalDateTime.of(2025, 2, 9, 20, 36, 0),
        epochTime = 1733800200
    )
}