package com.example.daboyeo_android.domain.repository

import com.example.daboyeo_android.data.model.sign.GoogleTokenRequest
import com.example.daboyeo_android.data.model.sign.GoogleTokenResponse
import com.example.daboyeo_android.data.api.interceptor.Result
import retrofit2.Response
import java.util.*

interface SignRepository{
    suspend fun signAuth(hashMap: HashMap<String, String>) : Result<String>
    suspend fun googleAccessToken(requestBody: GoogleTokenRequest): Result<GoogleTokenResponse>
}