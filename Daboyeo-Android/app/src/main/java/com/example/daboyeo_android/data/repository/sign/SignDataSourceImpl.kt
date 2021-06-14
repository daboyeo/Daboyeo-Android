package com.example.daboyeo_android.data.repository.sign

import com.example.daboyeo_android.data.api.service.SignService
import com.example.daboyeo_android.data.model.sign.GoogleTokenRequest
import com.example.daboyeo_android.data.model.sign.GoogleTokenResponse
import retrofit2.Response
import java.util.HashMap
import javax.inject.Inject

class SignDataSourceImpl(
    private val signService: SignService
): SignDataSource {
    override suspend fun signAuth(hashMap: HashMap<String, String>): Response<String> {
        return signService.signAuth(hashMap)
    }

    override suspend fun googleAccessToken(requestBody: GoogleTokenRequest): Response<GoogleTokenResponse> {
        return signService.getAccessToken(requestBody)
    }

}