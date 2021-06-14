package com.example.daboyeo_android.data.repository.post

import com.example.daboyeo_android.data.model.home.DetailPostData
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import org.json.JSONArray
import retrofit2.Response
import java.io.File

interface PostDataSource {
    suspend fun like(reportId: HashMap<String, Int>) : Response<Boolean>
    suspend fun addComment(hashMap: HashMap<String, String>) : Response<Unit>
    suspend fun deleteComment(hashMap: HashMap<String, String>) : Response<Unit>
    suspend fun deletePost(reportId: JsonObject) : Response<Unit>
    suspend fun getImage(uuid: String) : Response<File>
    suspend fun uploadFile(image: MultipartBody.Part): Response<String>
    suspend fun getDetailPost(id: Int): Response<DetailPostData>
}