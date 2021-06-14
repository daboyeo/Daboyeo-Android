package com.example.daboyeo_android.domain.repository

import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.data.api.interceptor.Result

interface SearchRepository {
    suspend fun getSearchTimeLine(content: String) : Result<PostListData>
}

