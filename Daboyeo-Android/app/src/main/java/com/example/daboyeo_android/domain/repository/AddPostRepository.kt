package com.example.daboyeo_android.domain.repository

import com.example.daboyeo_android.data.api.interceptor.Result
import okhttp3.MultipartBody
import org.json.JSONArray

interface AddPostRepository {
    suspend fun addPost(tags: JSONArray, images: JSONArray, hashMap: HashMap<String, String>): Result<String>
    suspend fun imageToServer(image: MultipartBody.Part): Result<Int>
}
