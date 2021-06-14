package com.example.daboyeo_android.data.repository.search

import com.example.daboyeo_android.data.model.home.PostListData
import retrofit2.Response

interface SearchDataSource {
    suspend fun getSearchTimeLine(content: String) : Response<PostListData>
}