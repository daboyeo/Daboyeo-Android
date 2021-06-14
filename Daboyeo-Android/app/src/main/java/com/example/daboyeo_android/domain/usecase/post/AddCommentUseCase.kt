package com.example.daboyeo_android.domain.usecase.post

import com.example.daboyeo_android.domain.repository.PostRepository
import com.example.daboyeo_android.presentation.viewModel.PostViewModel

class AddCommentUseCase(private val postRepository: PostRepository) {
    suspend fun execute(hashMap: HashMap<String, String>) = postRepository.addComment(hashMap)
}