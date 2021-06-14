package com.example.daboyeo_android.domain.usecase.profile

import com.example.daboyeo_android.domain.repository.ProfileRepository

class GetProfilePostsUseCase(private val repository: ProfileRepository) {
    suspend fun execute(userName: String) = repository.getProfilePosts(userName)
}