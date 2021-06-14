package com.example.daboyeo_android.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.domain.usecase.post.AddPostUseCase
import com.example.daboyeo_android.domain.usecase.profile.UploadImageUseCase

class AddPostViewModelFactory(
    private val addPostUseCase: AddPostUseCase,
    private val uploadImageUseCase: UploadImageUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddPostViewModel(addPostUseCase,uploadImageUseCase) as T
    }
}