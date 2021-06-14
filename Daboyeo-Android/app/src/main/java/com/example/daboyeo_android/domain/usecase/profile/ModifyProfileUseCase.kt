package com.example.daboyeo_android.domain.usecase.profile

import com.example.daboyeo_android.domain.repository.ProfileRepository

class ModifyProfileUseCase(private val repository: ProfileRepository) {
    suspend fun execute(hashMap: HashMap<String, String>) = repository.modifyProfile(hashMap)
}