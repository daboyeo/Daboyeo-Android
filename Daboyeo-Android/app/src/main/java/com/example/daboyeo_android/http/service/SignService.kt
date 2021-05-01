package com.example.daboyeo_android.http.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignService {
    @POST("/auth")
    suspend fun signAuth(
        @Body body: HashMap<String, String>
    ): Response<String>
} 