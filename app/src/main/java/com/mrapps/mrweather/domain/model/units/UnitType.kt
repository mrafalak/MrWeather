package com.mrapps.mrweather.domain.model.units

sealed class UnitType(val metric: Measurement, val imperial: Measurement) {
    data class Temperature(
        private val metricValue: Double,
        private val imperialValue: Double
    ) : UnitType(
        metric = Measurement(MeasurementUnit.TEMPERATURE_C, metricValue),
        imperial = Measurement(MeasurementUnit.TEMPERATURE_F, imperialValue)
    )

    data class Speed(val kmh: Double, val mph: Double) : UnitType(
        metric = Measurement(MeasurementUnit.SPEED_KMH, kmh),
        imperial = Measurement(MeasurementUnit.SPEED_MIH, mph)
    )

    data class Pressure(val mb: Double, val inHg: Double) : UnitType(
        metric = Measurement(MeasurementUnit.PRESSURE_MB, mb),
        imperial = Measurement(MeasurementUnit.PRESSURE_INHG, inHg)
    )
}

fun UnitType.formatToString(unitSystemType: UnitSystemType): String {
    val value = when (unitSystemType) {
        UnitSystemType.METRIC -> this.metric.value
        UnitSystemType.IMPERIAL -> this.imperial.value
    }

    val unit = when (unitSystemType) {
        UnitSystemType.METRIC -> this.metric.unit.unit
        UnitSystemType.IMPERIAL -> this.imperial.unit.unit
    }

    return "$value $unit"
}