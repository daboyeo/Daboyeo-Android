package com.example.daboyeo_android.domain.usecase.profile

import com.example.daboyeo_android.domain.repository.ProfileRepository

class GetMyProfileInfoUseCase(private val repository: ProfileRepository) {
    suspend fun execute() = repository.getMyProfile()
}