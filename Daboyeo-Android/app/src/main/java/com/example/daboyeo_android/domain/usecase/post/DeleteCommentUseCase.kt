package com.example.daboyeo_android.domain.usecase.post

import com.example.daboyeo_android.domain.repository.PostRepository

class DeleteCommentUseCase(private val postRepository: PostRepository) {
    suspend fun execute(hashMap: HashMap<String, String>) = postRepository.deleteComment(hashMap)
}