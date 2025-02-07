package com.mrapps.mrweather.data.remote.dto.weather_condition

import com.google.gson.annotations.SerializedName
import com.mrapps.mrweather.domain.model.units.ImperialFactory
import com.mrapps.mrweather.domain.model.units.MetricFactory
import com.mrapps.mrweather.domain.model.units.Unit

data class UnitDto(
    @SerializedName("Imperial") val imperial: ImperialDto,
    @SerializedName("Metric") val metric: MetricDto
) {
    fun toUnit(): Unit = Unit(
        imperial = ImperialFactory.fromUnitType(
            type = this.imperial.unitType,
            value = this.imperial.value
        ),
        metric = MetricFactory.fromUnitType(
            type = this.metric.unitType,
            value = this.metric.value
        )
    )
}