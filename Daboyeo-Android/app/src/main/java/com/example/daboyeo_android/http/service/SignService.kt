package com.example.daboyeo_android.http.service

import com.example.daboyeo_android.entity.sign.GoogleTokenRequest
import com.example.daboyeo_android.entity.sign.GoogleTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignService {
    @POST("user/auth")
    suspend fun signAuth(
        @Body body: HashMap<String, String>
    ): Response<String>

    @POST("oauth2/v4/token")
    suspend fun getAccessToken(
        @Body body: GoogleTokenRequest
    ): Response<GoogleTokenResponse>

} 