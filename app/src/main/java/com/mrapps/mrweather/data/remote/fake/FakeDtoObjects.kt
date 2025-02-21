package com.mrapps.mrweather.data.remote.fake

import com.mrapps.mrweather.data.remote.dto.forecast.DailyForecastDto
import com.mrapps.mrweather.data.remote.dto.forecast.ForecastPeriodDto
import com.mrapps.mrweather.data.remote.dto.forecast.MoonDto
import com.mrapps.mrweather.data.remote.dto.forecast.RelativeHumidityDto
import com.mrapps.mrweather.data.remote.dto.forecast.SunDto
import com.mrapps.mrweather.data.remote.dto.forecast.TemperaturesRangeDto
import com.mrapps.mrweather.data.remote.dto.location.AdministrativeAreaDto
import com.mrapps.mrweather.data.remote.dto.location.CityDto
import com.mrapps.mrweather.data.remote.dto.location.CountryDto
import com.mrapps.mrweather.data.remote.dto.location.RegionDto
import com.mrapps.mrweather.data.remote.dto.unit.UnitDoubleDto
import com.mrapps.mrweather.data.remote.dto.unit.UnitsDto
import com.mrapps.mrweather.data.remote.dto.weather_condition.DirectionDto
import com.mrapps.mrweather.data.remote.dto.weather_condition.PressureTendencyDto
import com.mrapps.mrweather.data.remote.dto.weather_condition.WeatherConditionsDto
import com.mrapps.mrweather.data.remote.dto.weather_condition.WindDto
import com.mrapps.mrweather.domain.model.weather_condition.WeatherIconType

object FakeDtoObjects {

    val baseTemperatureImperial = UnitDoubleDto(
        unit = "°F",
        unitType = 18,
        value = 51.8
    )
    val baseTemperatureMetric = UnitDoubleDto(
        unit = "°C",
        unitType = 17,
        value = 11.0
    )
    val baseRainImperial = UnitDoubleDto(
        unit = "in",
        unitType = 1,
        value = 51.8
    )
    val baseRainMetric = UnitDoubleDto(
        unit = "mm",
        unitType = 20,
        value = 0.0
    )
    val baseSnowImperial = UnitDoubleDto(
        unit = "in",
        unitType = 1,
        value = 51.8
    )
    val baseSnowMetric = UnitDoubleDto(
        unit = "cm",
        unitType = 19,
        value = 0.0
    )
    val baseIceImperial = UnitDoubleDto(
        unit = "in",
        unitType = 1,
        value = 51.8
    )
    val baseIceMetric = UnitDoubleDto(
        unit = "mm",
        unitType = 20,
        value = 0.0
    )
    val baseSpeedImperial = UnitDoubleDto(
        unit = "mi/h", unitType = 9, value = 15.0
    )
    val baseSpeedMetric = UnitDoubleDto(
        unit = "km/h", unitType = 7, value = 15.0
    )

    object Cities {
        val baseAdministrativeArea = AdministrativeAreaDto(
            id = "1",
            localizedName = "Mazowieckie",
            englishName = "Masovia",
            localizedType = "Województwo",
            englishType = "Voivodeship",
            countryId = ""
        )
        val baseCountry = CountryDto(
            id = "1", localizedName = "Polska", englishName = "Poland"
        )
        val baseRegion = RegionDto(
            id = "1", localizedName = "Europa", englishName = "Europe"
        )
        val cityDto = CityDto(
            id = "274663",
            localizedName = "Warszawa",
            englishName = "Warsaw",
            administrativeArea = baseAdministrativeArea,
            country = baseCountry,
            region = baseRegion
        )
    }

    object Conditions {
        val baseTemperature = UnitsDto(
            imperial = baseTemperatureImperial,
            metric = baseTemperatureMetric
        )
        val baseDirection = DirectionDto(
            degrees = 250,
            english = "W",
            localized = "Z"
        )
        val baseSpeed = UnitDoubleDto(
            unit = "km/h",
            unitType = 7,
            value = 18.0
        )
        val baseWind = WindDto(
            direction = baseDirection,
            speed = baseSpeed
        )
        val basePressureImperial = UnitDoubleDto(
            unit = "inHg",
            unitType = 27,
            value = 30.0
        )
        val basePressureMetric = UnitDoubleDto(
            unit = "mb",
            unitType = 28,
            value = 1018.0
        )
        val basePressure = UnitsDto(
            imperial = basePressureImperial,
            metric = basePressureMetric
        )
        val basePressureTendency = PressureTendencyDto(
            code = "F"
        )

        val conditionsDto = WeatherConditionsDto(
            weatherText = "Zachmurzenie",
            weatherIcon = WeatherIconType.CLOUDY.ordinal,
            temperature = baseTemperature,
            realFeelTemperature = baseTemperature,
            realFeelTemperatureShade = baseTemperature,
            relativeHumidity = 82,
            hasPrecipitation = false,
            precipitationType = "",
            wind = baseWind,
            cloudCover = 60,
            pressure = basePressure,
            pressureTendency = basePressureTendency,
            localObservationDateTime = "2025-02-09T20:36:00",
            epochTime = 1733800200
        )
    }

    object Forecast {
        val baseMetricTemperature = TemperaturesRangeDto(
            maximum = baseTemperatureMetric,
            minimum = baseTemperatureMetric
        )
        val baseImperialTemperature = TemperaturesRangeDto(
            maximum = baseTemperatureMetric,
            minimum = baseTemperatureMetric
        )
        val baseRelativeHumidity = RelativeHumidityDto(
            average = 70,
            maximum = 85,
            minimum = 55
        )
        val baseDirection = DirectionDto(
            degrees = 180,
            english = "South",
            localized = "Południowy"
        )
        val baseWind = WindDto(
            direction = baseDirection,
            speed = baseSpeedMetric
        )

        val baseForecastPeriod = ForecastPeriodDto(
            icon = 13,
            iconPhrase = "Częściowe zachmurzenie",
            shortPhrase = "Chłodno i słonecznie",
            longPhrase = "Słoneczny dzień z okresowymi chmurami, chłodno",
            cloudCover = 30,
            relativeHumidity = baseRelativeHumidity,
            hasPrecipitation = false,
            precipitationProbability = 10,
            hoursOfPrecipitation = 0.0,
            rain = baseRainMetric,
            rainProbability = 5,
            hoursOfRain = 0.0,
            snow = baseSnowMetric,
            snowProbability = 20,
            hoursOfSnow = 0,
            ice = baseIceMetric,
            iceProbability = 5,
            hoursOfIce = 0,
            wind = baseWind
        )
        val baseSun = SunDto(
            epochRise = 1733750400,
            epochSet = 1733793600,
            rise = "07:30",
            set = "16:30"
        )
        val baseMoon = MoonDto(
            epochRise = 1733790000,
            epochSet = 1733828400,
            phase = "Ubywający Księżyc",
            rise = "16:00",
            set = "02:00",
            age = 10
        )

        val metricForecastDto = DailyForecastDto(
            date = "2025-02-11T07:00:00+01:00",
            epochDate = 1739253600,
            temperatures = baseMetricTemperature,
            realFeelTemperature = baseMetricTemperature,
            realFeelTemperatureShade = baseMetricTemperature,
            day = baseForecastPeriod,
            night = baseForecastPeriod.copy(
                icon = 33,
                iconPhrase = "Przeważnie bezchmurnie",
                shortPhrase = "Zimno i przejrzyście",
                longPhrase = "Bezchmurna noc, zimno",
            ),
            sun = baseSun,
            moon = baseMoon
        )

        val fiveDaysMetricForecastDto = listOf(
            metricForecastDto,
            metricForecastDto,
            metricForecastDto,
            metricForecastDto,
            metricForecastDto
        )
    }
}