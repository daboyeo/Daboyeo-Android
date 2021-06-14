package com.example.daboyeo_android.data.repository.sign

import com.example.daboyeo_android.data.model.sign.GoogleTokenRequest
import com.example.daboyeo_android.data.model.sign.GoogleTokenResponse
import retrofit2.Response
import java.util.HashMap

interface SignDataSource {
    suspend fun signAuth(hashMap: HashMap<String, String>) : Response<String>
    suspend fun googleAccessToken(requestBody: GoogleTokenRequest): Response<GoogleTokenResponse>
}