package com.mrapps.mrweather.data.remote.dto.unit

import com.mrapps.mrweather.domain.model.units.UnitType
import com.mrapps.mrweather.ui.util.roundToSingleDecimal

fun <T> calculateUnit(
    unitsDto: UnitsDto,
    createUnit: (Double, Double) -> T
): T? {
    val metricValue = unitsDto.metric.value?.let { roundToSingleDecimal(it) }
    val imperialValue = unitsDto.imperial.value?.let { roundToSingleDecimal(it) }

    return if (metricValue != null && imperialValue != null) {
        createUnit(metricValue, imperialValue)
    } else {
        null
    }
}

fun calculateTemperature(temperature: UnitsDto): UnitType.Temperature? {
    return calculateUnit(temperature) { metric, imperial ->
        UnitType.Temperature(metric, imperial)
    }
}

fun calculatePressure(pressure: UnitsDto): UnitType.Pressure? {
    return calculateUnit(pressure) { metric, imperial ->
        UnitType.Pressure(metric, imperial)
    }
}