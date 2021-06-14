package com.example.daboyeo_android.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.domain.usecase.post.*

class PostViewModelFactory(
    private val addCommentUseCase: AddCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val getDetailPostUseCase: GetDetailPostUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val likeUseCase: LikeUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(
            addCommentUseCase,
            deleteCommentUseCase,
            deletePostUseCase,
            getDetailPostUseCase,
            getImageUseCase,
            likeUseCase
        ) as T
    }
}