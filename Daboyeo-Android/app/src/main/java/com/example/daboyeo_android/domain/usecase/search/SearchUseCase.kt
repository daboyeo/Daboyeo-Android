package com.example.daboyeo_android.domain.usecase.search

import com.example.daboyeo_android.domain.repository.SearchRepository

class SearchUseCase(private val repository: SearchRepository) {
    suspend fun execute(content: String) = repository.getSearchTimeLine(content)
}