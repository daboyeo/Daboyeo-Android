package com.example.daboyeo_android.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.domain.usecase.profile.*

class ProfileViewModelFactory (
    private val getProfileInfoUseCase: GetProfileInfoUseCase,
    private val getMyProfileInfoUseCase: GetMyProfileInfoUseCase,
    private val getProfilePostsUseCase: GetProfilePostsUseCase,
    private val uploadImageUseCase: UploadImageUseCase,
    private val modifyProfileUseCase: ModifyProfileUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(
                getProfileInfoUseCase,
                getMyProfileInfoUseCase,
                getProfilePostsUseCase,
                uploadImageUseCase,
                modifyProfileUseCase
        ) as T
    }

}