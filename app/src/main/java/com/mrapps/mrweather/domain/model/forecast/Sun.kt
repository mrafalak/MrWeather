package com.mrapps.mrweather.domain.model.forecast

data class Sun(
    val epochRise: Int,
    val epochSet: Int,
    val rise: String,
    val set: String
)