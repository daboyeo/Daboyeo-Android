package com.example.daboyeo_android.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daboyeo_android.domain.usecase.search.SearchUseCase

class SearchViewModelFactory(
    private val searchUseCase: SearchUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(
            searchUseCase
        ) as T
    }
}