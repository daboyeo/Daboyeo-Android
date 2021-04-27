package com.example.daboyeo_android.http.interceptor

import retrofit2.Response

suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
    return try {
        val myResponse = call.invoke()

        if (myResponse.isSuccessful) {
            Result.Success(myResponse.body()!!, myResponse.code())
        } else {
            Result.Error(myResponse.message())
        }

    } catch (e: Exception) {
        Result.Error(e.message ?: "Internet error runs")
    }
}