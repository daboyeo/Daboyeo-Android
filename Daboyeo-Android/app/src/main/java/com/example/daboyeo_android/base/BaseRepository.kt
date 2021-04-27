package com.example.daboyeo_android.base

import com.example.daboyeo_android.http.interceptor.safeApiCall
import com.example.daboyeo_android.http.interceptor.Result
import com.example.daboyeo_android.util.DaboyeoApplication
import retrofit2.Response

open class BaseRepository {
    fun getAccessToken(): String? {
        return DaboyeoApplication.pref!!.getToken()
    }

    suspend fun <T: Any> mappingToResult(result: suspend () -> Response<T>): Result<T> {
        return safeApiCall(call=result)
    }
}