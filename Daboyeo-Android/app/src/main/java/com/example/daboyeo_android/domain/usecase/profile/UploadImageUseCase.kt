package com.example.daboyeo_android.domain.usecase.profile

import com.example.daboyeo_android.domain.repository.ProfileRepository
import okhttp3.MultipartBody

class UploadImageUseCase(private val repository: ProfileRepository) {
    suspend fun execute(image: MultipartBody.Part) = repository.uploadImage(image)
}