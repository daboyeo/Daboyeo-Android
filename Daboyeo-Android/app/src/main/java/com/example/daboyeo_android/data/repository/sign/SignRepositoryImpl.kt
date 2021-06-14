package com.example.daboyeo_android.data.repository.sign

import com.example.daboyeo_android.data.api.interceptor.Result
import com.example.daboyeo_android.data.model.sign.GoogleTokenRequest
import com.example.daboyeo_android.data.model.sign.GoogleTokenResponse
import com.example.daboyeo_android.domain.repository.SignRepository
import retrofit2.Response
import java.util.HashMap

class SignRepositoryImpl(
    private val signDataSource: SignDataSource
): SignRepository{
    override suspend fun signAuth(hashMap: HashMap<String, String>): Result<String> {
        return responseToSignAuth(signDataSource.signAuth(hashMap))
    }

    override suspend fun googleAccessToken(requestBody: GoogleTokenRequest): Result<GoogleTokenResponse> {
        return responseToGoogleAccessToken(signDataSource.googleAccessToken(requestBody))
    }

    private fun responseToSignAuth(response: Response<String>): Result<String> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }

    private fun responseToGoogleAccessToken(response: Response<GoogleTokenResponse>) : Result<GoogleTokenResponse> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }
}