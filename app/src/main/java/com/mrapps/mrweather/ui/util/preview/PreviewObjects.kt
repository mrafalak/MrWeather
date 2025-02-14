package com.mrapps.mrweather.ui.util.preview

import com.mrapps.mrweather.domain.model.forecast.DailyForecast
import com.mrapps.mrweather.domain.model.forecast.ForecastPeriod
import com.mrapps.mrweather.domain.model.forecast.Moon
import com.mrapps.mrweather.domain.model.forecast.RelativeHumidity
import com.mrapps.mrweather.domain.model.forecast.Sun
import com.mrapps.mrweather.domain.model.forecast.TemperaturesRange
import com.mrapps.mrweather.domain.model.location.AdministrativeArea
import com.mrapps.mrweather.domain.model.location.City
import com.mrapps.mrweather.domain.model.location.Country
import com.mrapps.mrweather.domain.model.location.Region
import com.mrapps.mrweather.domain.model.units.UnitType
import com.mrapps.mrweather.domain.model.weather_condition.Direction
import com.mrapps.mrweather.domain.model.weather_condition.PrecipitationType
import com.mrapps.mrweather.domain.model.weather_condition.PressureTendency
import com.mrapps.mrweather.domain.model.weather_condition.WeatherConditions
import com.mrapps.mrweather.domain.model.weather_condition.WeatherIconType
import com.mrapps.mrweather.domain.model.weather_condition.Wind
import java.time.LocalDateTime

object PreviewObjects {
    object Cities {
        val baseAdministrativeArea = AdministrativeArea(
            id = "1",
            localizedName = "Mazowieckie",
            englishName = "Masovia",
            localizedType = "Województwo",
            englishType = "Voivodeship"
        )
        val baseCountry = Country(
            id = "1",
            localizedName = "Polska",
            englishName = "Poland"
        )
        val baseRegion = Region(
            id = "1",
            localizedName = "Europa",
            englishName = "Europe"
        )
        val city = City(
            id = "274663",
            localizedName = "Warszawa",
            englishName = "Warsaw",
            administrativeArea = baseAdministrativeArea,
            country = baseCountry,
            region = baseRegion
        )
    }

    object Conditions {
        val baseTemperature = UnitType.Temperature(
            metricValue = 11.0,
            imperialValue = 51.8
        )
        val basePressure = UnitType.Pressure(
            mb = 1018.0,
            inHg = 30.0
        )
        val baseSpeed = UnitType.Speed(
            kmh = 18.0,
            mph = 18.0 * 0.621371
        )
        val baseDirection = Direction(
            degrees = 250,
            english = "W",
            localized = "Z"
        )
        val baseWind = Wind(
            direction = baseDirection,
            speed = baseSpeed
        )
        val conditions = WeatherConditions(
            weatherText = "Zachmurzenie",
            weatherIcon = WeatherIconType.CLOUDY,
            temperature = baseTemperature,
            realFeelTemperature = baseTemperature,
            realFeelTemperatureShade = baseTemperature,
            relativeHumidity = 82,
            hasPrecipitation = false,
            precipitationType = PrecipitationType.NONE,
            wind = baseWind,
            cloudCover = 60,
            pressure = basePressure,
            pressureTendency = PressureTendency.STEADY,
            localObservationDateTime = LocalDateTime.of(2025, 2, 9, 20, 36, 0),
            epochTime = 1733800200
        )
    }

    object Forecast {
        val baseTemperature = UnitType.Temperature(
            metricValue = 10.1,
            imperialValue = 10.1 * 1.8 + 32
        )
        val baseTemperatures = TemperaturesRange(
            maximum = baseTemperature,
            minimum = baseTemperature.copy(metricValue = 9.9, imperialValue = 9.9 * 1.8 + 32)
        )
        val baseRelativeHumidity = RelativeHumidity(
            average = 70,
            maximum = 85,
            minimum = 55
        )
        val baseRain = UnitType.SmallLength(
            mm = 0.0,
            inch = 0.0
        )
        val baseSnow = UnitType.Length(
            cm = 0.0,
            inch = 0.0
        )
        val baseWind = Wind(
            direction = Direction(
                degrees = 180,
                english = "South",
                localized = "Południowy"
            ),
            speed = UnitType.Speed(
                kmh = 15.0,
                mph = 15.0 * 0.621371
            )
        )
        val baseSun = Sun(
            epochRise = 1733750400,
            epochSet = 1733793600,
            rise = "07:30",
            set = "16:30"
        )
        val baseMoon = Moon(
            age = 10,
            epochRise = 1733790000,
            epochSet = 1733828400,
            phase = "Ubywający Księżyc",
            rise = "16:00",
            set = "02:00"
        )
        val baseForecastPeriod = ForecastPeriod(
            icon = WeatherIconType.SUNNY_CLEAR,
            shortPhrase = "Chłodno i słonecznie",
            cloudCover = 30,
            relativeHumidity = baseRelativeHumidity,
            hasPrecipitation = false,
            precipitationProbability = 10,
            hoursOfPrecipitation = 0.0,
            rain = baseRain,
            rainProbability = 5,
            hoursOfRain = 0.0,
            snow = baseSnow,
            snowProbability = 20,
            hoursOfSnow = 0,
            ice = baseRain,
            iceProbability = 5,
            hoursOfIce = 0,
            wind = baseWind
        )
        val baseForecast = DailyForecast(
            date = LocalDateTime.of(2025, 2, 11, 7, 0),
            epochDate = 1739253600,
            temperatures = baseTemperatures,
            realFeelTemperature = baseTemperatures,
            realFeelTemperatureShade = baseTemperatures,
            day = baseForecastPeriod,
            night = baseForecastPeriod.copy(
                icon = WeatherIconType.NIGHT_CLEAR,
                shortPhrase = "Zimno i przejrzyście"
            ),
            sun = baseSun,
            moon = baseMoon
        )
        val fiveDaysForecast = listOf(
            baseForecast,
            baseForecast,
            baseForecast,
            baseForecast,
            baseForecast,
        )
    }
}