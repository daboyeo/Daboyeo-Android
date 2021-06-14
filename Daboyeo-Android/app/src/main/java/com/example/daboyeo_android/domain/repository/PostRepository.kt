package com.example.daboyeo_android.domain.repository

import com.example.daboyeo_android.data.api.interceptor.Result
import com.example.daboyeo_android.data.model.home.DetailPostData
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import org.json.JSONArray
import java.io.File

interface PostRepository{
    suspend fun like(reportId: HashMap<String, Int>) : Result<Boolean>
    suspend fun addComment(hashMap: HashMap<String, String>) : Result<Unit>
    suspend fun deleteComment(hashMap: HashMap<String, String>) : Result<Unit>
    suspend fun deletePost(reportId: JsonObject) : Result<Unit>
    suspend fun getImage(uuid: String) : Result<File>
    suspend fun imageToServer(image: MultipartBody.Part): Result<String>
    suspend fun getDetailPost(id: Int) :Result<DetailPostData>
}