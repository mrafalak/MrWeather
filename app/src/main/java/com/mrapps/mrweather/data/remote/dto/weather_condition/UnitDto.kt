package com.mrapps.mrweather.data.remote.dto.weather_condition

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.units.Imperial
import com.mrapps.mrweather.domain.model.units.Metric
import com.mrapps.mrweather.domain.model.units.Unit

data class UnitDto(
    @SerializedName("Imperial") val imperial: ImperialDto,
    @SerializedName("Metric") val metric: MetricDto
) {
    fun toUnit(): Unit = Unit(
        imperial = Imperial.fromUnitType(
            type = this.imperial.unitType,
            value = this.imperial.value
        ),
        metric = Metric.fromUnitType(
            type = this.metric.unitType,
            value = this.metric.value
        )
    )
}