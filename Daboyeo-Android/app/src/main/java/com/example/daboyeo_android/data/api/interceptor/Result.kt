package com.example.daboyeo_android.data.api.interceptor

sealed class Result<T>(
    val data: T? = null,
    val code: Int? = null
) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(code: Int, data: T? = null) : Result<T>(data, code)
}
