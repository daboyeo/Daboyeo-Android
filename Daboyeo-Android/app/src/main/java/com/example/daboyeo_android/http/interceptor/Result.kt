package com.example.daboyeo_android.http.interceptor

sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T, val code: Int) : Result<T>()
    data class Error(val exception: String) : Result<Nothing>()
}