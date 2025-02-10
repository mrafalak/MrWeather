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

    data class Length(
        private val cm: Double,
        private val inch: Double
    ) : UnitType(
        metric = Measurement(MeasurementUnit.LENGTH_CM, cm),
        imperial = Measurement(MeasurementUnit.LENGTH_IN, inch)
    )

    data class SmallLength(
        private val mm: Double,
        private val inch: Double
    ) : UnitType(
        metric = Measurement(MeasurementUnit.LENGTH_MM, mm),
        imperial = Measurement(MeasurementUnit.LENGTH_IN, inch)
    )
}

fun UnitType.formatToString(unitSystemType: UnitSystemType, appendSpace: Boolean = true): String {
    val (value, unit) = when (unitSystemType) {
        UnitSystemType.METRIC -> this.metric.value to this.metric.unit.unit
        UnitSystemType.IMPERIAL -> this.imperial.value to this.imperial.unit.unit
    }
    return if (appendSpace) "$value $unit" else "$value$unit"
}