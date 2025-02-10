package com.mrapps.mrweather.domain.model.forecast

data class Moon(
    val age: Int,
    val epochRise: Int,
    val epochSet: Int,
    val phase: String,
    val rise: String,
    val set: String
)