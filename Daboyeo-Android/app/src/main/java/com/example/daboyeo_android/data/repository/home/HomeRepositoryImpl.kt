package com.example.daboyeo_android.data.repository.home

import com.example.daboyeo_android.data.api.interceptor.Result
import com.example.daboyeo_android.data.model.home.DetailPostData
import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.domain.repository.HomeRepository
import org.json.JSONArray
import retrofit2.Response

class HomeRepositoryImpl(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getTimeLine(): Result<PostListData> {
        return responseToTimeLine(homeDataSource.getTimeLine())
    }

    override suspend fun addPost(
            tags: JSONArray,
            images: JSONArray,
            hashMap: HashMap<String, String>
    ): Result<String> {
        return responseToAddPost(homeDataSource.addPost(tags,images,hashMap))
    }

    private fun responseToTimeLine(response: Response<PostListData>): Result<PostListData> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }

    private fun responseToAddPost(response: Response<String>): Result<String> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }

}