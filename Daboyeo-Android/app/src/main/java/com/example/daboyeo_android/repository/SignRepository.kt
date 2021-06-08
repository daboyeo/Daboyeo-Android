package com.example.daboyeo_android.repository

import com.example.daboyeo_android.base.BaseRepository
import com.example.daboyeo_android.entity.sign.GoogleTokenRequest
import com.example.daboyeo_android.entity.sign.GoogleTokenResponse
import com.example.daboyeo_android.http.interceptor.DaboyeoConnect
import com.example.daboyeo_android.http.interceptor.Result
import java.util.*

class SignRepository : BaseRepository(){
    suspend fun signAuth(hashMap: HashMap<String, String>) : Result<String> {
        return mappingToResult { DaboyeoConnect.getAPI().signAuth(hashMap) }
    }

    suspend fun googleAccessToken(requestBody: GoogleTokenRequest): Result<GoogleTokenResponse> {
        return mappingToResult { DaboyeoConnect.getGoogleToken().getAccessToken(requestBody)}
    }
}