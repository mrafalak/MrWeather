package com.mrapps.mrweather.data.remote.dto.forecast

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.data.remote.dto.unit.UnitDoubleDto
import com.mrapps.mrweather.domain.model.forecast.TemperaturesRange
import com.mrapps.mrweather.domain.model.units.UnitType
import com.mrapps.mrweather.ui.util.convertTemperature
import com.mrapps.mrweather.ui.util.roundToSingleDecimal

data class TemperaturesRangeDto(
    @SerializedName("Maximum") val maximum: UnitDoubleDto,
    @SerializedName("Minimum") val minimum: UnitDoubleDto
) {
    fun toTemperaturesRange(metric: Boolean): TemperaturesRange? {
        return if (minimum.value == null || maximum.value == null) {
            null
        } else {
            val (minMetric, minImperial) = convertTemperature(minimum.value, metric)
            val (maxMetric, maxImperial) = convertTemperature(maximum.value, metric)

            val minimumValue =
                UnitType.Temperature(
                    metricValue = roundToSingleDecimal(minMetric),
                    imperialValue = roundToSingleDecimal(minImperial)
                )
            val maximumValue =
                UnitType.Temperature(
                    metricValue = roundToSingleDecimal(maxMetric),
                    imperialValue = roundToSingleDecimal(maxImperial)
                )

            TemperaturesRange(
                minimum = minimumValue,
                maximum = maximumValue
            )
        }
    }
}