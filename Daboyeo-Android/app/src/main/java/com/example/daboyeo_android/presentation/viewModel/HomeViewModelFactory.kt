package com.example.daboyeo_android.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.domain.usecase.home.GetTimeLineUseCase
import com.example.daboyeo_android.domain.usecase.post.GetDetailPostUseCase

class HomeViewModelFactory(
    private val getTimeLineUseCase: GetTimeLineUseCase
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(
                getTimeLineUseCase
        ) as T
    }

}