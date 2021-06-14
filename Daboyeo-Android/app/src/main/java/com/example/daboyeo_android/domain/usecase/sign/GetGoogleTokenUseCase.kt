package com.example.daboyeo_android.domain.usecase.sign

import com.example.daboyeo_android.data.model.sign.GoogleTokenRequest
import com.example.daboyeo_android.domain.repository.SignRepository

class GetGoogleTokenUseCase(private val signRepository: SignRepository) {
    suspend fun execute(requestBody: GoogleTokenRequest) = signRepository.googleAccessToken(requestBody)
}