package com.example.daboyeo_android.domain.usecase.post

import com.example.daboyeo_android.domain.repository.PostRepository

class LikeUseCase(private val postRepository: PostRepository){
    suspend fun execute(id: HashMap<String, Int>) = postRepository.like(id)
}