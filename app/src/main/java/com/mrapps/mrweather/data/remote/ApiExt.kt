package com.mrapps.mrweather.data.remote

import com.mrapps.mrweather.domain.model.util.Result

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Result<T> {
    return try {
        Result.Success(apiCall())
    } catch (e: Exception) {
        Result.Exception(e)
    }
}