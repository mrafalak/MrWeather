package com.mrapps.mrweather.domain.model.weather_condition

import androidx.compose.ui.graphics.Color
import com.mrapps.mrweather.R
import com.mrapps.mrweather.ui.theme.*


enum class WeatherIconType(val id: Int, val resId: Int, val stringResId: Int, val colorRes: Color) {
    SUNNY_CLEAR(1, R.drawable.ic_weather_sunny, R.string.weather_sunny, YellowSun),
    MOSTLY_SUNNY(2, R.drawable.ic_weather_sunny, R.string.weather_mostly_sunny, YellowSun),
    PARTLY_SUNNY(
        3,
        R.drawable.ic_weather_partly_cloudy,
        R.string.weather_partly_sunny,
        LightGrayCloud
    ),
    INTERMITTENT_CLOUDS(
        4,
        R.drawable.ic_weather_partly_cloudy,
        R.string.weather_intermittent_clouds,
        LightGrayCloud
    ),
    HAZY_SUNSHINE(5, R.drawable.ic_weather_fog, R.string.weather_hazy_sunshine, FogGray),
    MOSTLY_CLOUDY(6, R.drawable.ic_weather_cloudy, R.string.weather_mostly_cloudy, DarkGrayCloud),
    CLOUDY(7, R.drawable.ic_weather_cloudy, R.string.weather_cloudy, DarkGrayCloud),
    DREARY(8, R.drawable.ic_weather_cloudy, R.string.weather_dreary, DarkGrayCloud),
    FOG(11, R.drawable.ic_weather_fog, R.string.weather_fog, FogGray),
    SHOWERS(12, R.drawable.ic_weather_rain, R.string.weather_showers, BlueRain),
    MOSTLY_CLOUDY_SHOWERS(
        13,
        R.drawable.ic_weather_rain,
        R.string.weather_mostly_cloudy_showers,
        BlueRain
    ),
    PARTLY_SUNNY_SHOWERS(
        14,
        R.drawable.ic_weather_rain,
        R.string.weather_partly_sunny_showers,
        BlueRain
    ),
    T_STORMS(
        15,
        R.drawable.ic_weather_thunderstorm,
        R.string.weather_thunderstorms,
        DarkPurpleStorm
    ),
    MOSTLY_CLOUDY_T_STORMS(
        16,
        R.drawable.ic_weather_thunderstorm,
        R.string.weather_mostly_cloudy_t_storms,
        DarkPurpleStorm
    ),
    PARTLY_SUNNY_T_STORMS(
        17,
        R.drawable.ic_weather_thunderstorm,
        R.string.weather_partly_sunny_t_storms,
        DarkPurpleStorm
    ),
    RAIN(18, R.drawable.ic_weather_rain, R.string.weather_rain, BlueRain),
    FLURRIES(19, R.drawable.ic_weather_snow, R.string.weather_flurries, LightBlueSnow),
    MOSTLY_CLOUDY_FLURRIES(
        20,
        R.drawable.ic_weather_snow,
        R.string.weather_mostly_cloudy_flurries,
        LightBlueSnow
    ),
    PARTLY_SUNNY_FLURRIES(
        21,
        R.drawable.ic_weather_snow,
        R.string.weather_partly_sunny_flurries,
        LightBlueSnow
    ),
    SNOW(22, R.drawable.ic_weather_snow, R.string.weather_snow, WhiteSnow),
    MOSTLY_CLOUDY_SNOW(
        23,
        R.drawable.ic_weather_snow,
        R.string.weather_mostly_cloudy_snow,
        WhiteSnow
    ),
    ICE(24, R.drawable.ic_weather_ice, R.string.weather_ice, IceBlue),
    SLEET(25, R.drawable.ic_weather_ice, R.string.weather_sleet, IceBlue),
    FREEZING_RAIN(26, R.drawable.ic_weather_ice, R.string.weather_freezing_rain, IceBlue),
    RAIN_AND_SNOW(29, R.drawable.ic_weather_snow, R.string.weather_rain_and_snow, LightBlueSnow),
    HOT(30, R.drawable.ic_weather_hot, R.string.weather_hot, RedHot),
    COLD(31, R.drawable.ic_weather_cold, R.string.weather_cold, BlueCold),
    WINDY(32, R.drawable.ic_weather_windy, R.string.weather_windy, GrayWindy),
    NIGHT_CLEAR(33, R.drawable.ic_weather_night_clear, R.string.weather_night_clear, DarkBlueNight),
    NIGHT_MOSTLY_CLEAR(
        34,
        R.drawable.ic_weather_night_clear,
        R.string.weather_night_mostly_clear,
        DarkBlueNight
    ),
    NIGHT_PARTLY_CLOUDY(
        35,
        R.drawable.ic_weather_night_partly_cloudy,
        R.string.weather_night_partly_cloudy,
        DarkGrayCloud
    ),
    NIGHT_INTERMITTENT_CLOUDS(
        36,
        R.drawable.ic_weather_night_partly_cloudy,
        R.string.weather_night_intermittent_clouds,
        DarkGrayCloud
    ),
    NIGHT_HAZY_MOONLIGHT(
        37,
        R.drawable.ic_weather_fog,
        R.string.weather_night_hazy_moonlight,
        FogGray
    ),
    NIGHT_MOSTLY_CLOUDY(
        38,
        R.drawable.ic_weather_night_cloudy,
        R.string.weather_night_mostly_cloudy,
        DarkGrayCloud
    ),
    NIGHT_PARTLY_CLOUDY_SHOWERS(
        39,
        R.drawable.ic_weather_rain,
        R.string.weather_night_partly_cloudy_showers,
        BlueRain
    ),
    NIGHT_MOSTLY_CLOUDY_SHOWERS(
        40,
        R.drawable.ic_weather_rain,
        R.string.weather_night_mostly_cloudy_showers,
        BlueRain
    ),
    NIGHT_PARTLY_CLOUDY_T_STORMS(
        41,
        R.drawable.ic_weather_thunderstorm,
        R.string.weather_night_partly_cloudy_t_storms,
        DarkPurpleStorm
    ),
    NIGHT_MOSTLY_CLOUDY_T_STORMS(
        42,
        R.drawable.ic_weather_thunderstorm,
        R.string.weather_night_mostly_cloudy_t_storms,
        DarkPurpleStorm
    ),
    NIGHT_MOSTLY_CLOUDY_FLURRIES(
        43,
        R.drawable.ic_weather_snow,
        R.string.weather_night_mostly_cloudy_flurries,
        LightBlueSnow
    ),
    NIGHT_MOSTLY_CLOUDY_SNOW(
        44,
        R.drawable.ic_weather_snow,
        R.string.weather_night_mostly_cloudy_snow,
        WhiteSnow
    ),
    UNKNOWN(-1, R.drawable.ic_weather_unknown, R.string.weather_unknown, GrayUnknown);

    companion object {
        fun fromTypeId(id: Int): WeatherIconType {
            return entries.find { it.id == id } ?: UNKNOWN
        }
    }
}