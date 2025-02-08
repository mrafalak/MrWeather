package com.mrapps.mrweather.data.local.util

import com.mrapps.mrweather.domain.model.util.Result

suspend fun <T> safeDatabaseOperation(
    operation: suspend () -> T
): Result<T> {
    return try {
        Result.Success(operation())
    } catch (e: Exception) {
        Result.Exception(e)
    }
}