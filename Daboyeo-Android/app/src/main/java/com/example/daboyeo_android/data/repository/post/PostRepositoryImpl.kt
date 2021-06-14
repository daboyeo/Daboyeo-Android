package com.example.daboyeo_android.data.repository.post

import com.example.daboyeo_android.data.api.interceptor.Result
import com.example.daboyeo_android.data.model.home.DetailPostData
import com.example.daboyeo_android.domain.repository.PostRepository
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import org.json.JSONArray
import retrofit2.Response
import java.io.File

class PostRepositoryImpl(
        private val postDataSource: PostDataSource
): PostRepository{
    override suspend fun like(reportId: HashMap<String, Int>): Result<Boolean> {
        return responseToLike(postDataSource.like(reportId))
    }

    override suspend fun addComment(hashMap: HashMap<String, String>): Result<Unit> {
        return responseToResult(postDataSource.addComment(hashMap))
    }

    override suspend fun deleteComment(hashMap: HashMap<String, String>): Result<Unit> {
        return responseToResult(postDataSource.deleteComment(hashMap))
    }

    override suspend fun deletePost(reportId: JsonObject): Result<Unit> {
        return responseToResult(postDataSource.deletePost(reportId))
    }

    override suspend fun getImage(uuid: String): Result<File> {
        return responseToGetImage(postDataSource.getImage(uuid))
    }

    override suspend fun imageToServer(image: MultipartBody.Part): Result<String> {
        return responseToUploadFile(postDataSource.uploadFile(image))
    }

    override suspend fun getDetailPost(id: Int): Result<DetailPostData> {
        return responseToDetailPost(postDataSource.getDetailPost(id))
    }


    private fun responseToDetailPost(response: Response<DetailPostData>): Result<DetailPostData> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }

    private fun responseToLike(response: Response<Boolean>): Result<Boolean> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }

    private fun responseToUploadFile(response: Response<String>): Result<String> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }

    private fun responseToResult(response: Response<Unit>) : Result<Unit> {
        if(response.isSuccessful) {
            response.body()?.let{
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }

    private fun responseToGetImage(response: Response<File>): Result<File> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }

}