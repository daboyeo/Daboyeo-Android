package com.example.daboyeo_android.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.domain.usecase.sign.GetGoogleTokenUseCase
import com.example.daboyeo_android.domain.usecase.sign.LoginUseCase

class SignViewModelFactory(
        private val loginUseCase: LoginUseCase,
        private val getGoogleTokenUseCase: GetGoogleTokenUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignViewModel(
                loginUseCase,
        getGoogleTokenUseCase
        ) as T
    }

}
