package com.example.daboyeo_android.domain.usecase.post

import com.example.daboyeo_android.domain.repository.PostRepository
import com.google.gson.JsonObject

class DeletePostUseCase(private val postRepository: PostRepository) {
    suspend fun execute(id: JsonObject) = postRepository.deletePost(id)
}