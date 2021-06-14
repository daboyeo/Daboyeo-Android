package com.example.daboyeo_android.domain.usecase.profile

import com.example.daboyeo_android.domain.repository.ProfileRepository

class GetProfileInfoUseCase(private val repository: ProfileRepository) {
    suspend fun execute(userName: String) = repository.getUserProfile(userName)
}