package com.example.daboyeo_android.domain.usecase.home

import com.example.daboyeo_android.data.api.interceptor.Result
import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.domain.repository.HomeRepository

class GetTimeLineUseCase(private val repository: HomeRepository) {
    suspend fun getTimeLine() : Result<PostListData> = repository.getTimeLine()
}