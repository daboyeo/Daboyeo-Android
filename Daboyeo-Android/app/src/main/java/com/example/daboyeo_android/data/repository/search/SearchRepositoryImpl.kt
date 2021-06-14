package com.example.daboyeo_android.data.repository.search

import com.example.daboyeo_android.data.api.interceptor.Result
import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.domain.repository.SearchRepository
import retrofit2.Response

class SearchRepositoryImpl(
    private val searchDataSource: SearchDataSource
): SearchRepository{
    override suspend fun getSearchTimeLine(content: String): Result<PostListData> {
        return responseToSearchTimeLine(searchDataSource.getSearchTimeLine(content))
    }

    private fun responseToSearchTimeLine(response: Response<PostListData>): Result<PostListData> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }
}