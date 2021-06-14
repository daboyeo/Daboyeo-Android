package com.example.daboyeo_android.domain.usecase.post

import com.example.daboyeo_android.domain.repository.PostRepository

class GetDetailPostUseCase(private val postRepository: PostRepository) {
    suspend fun execute(id: Int) = postRepository.getDetailPost(id)
}