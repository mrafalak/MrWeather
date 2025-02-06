package com.mrapps.mrweather.domain.model.util

sealed class Result<out D> {
    data class Success<out D>(val data: D) : Result<D>()
    data class Exception(val error: Throwable) : Result<Nothing>()
}