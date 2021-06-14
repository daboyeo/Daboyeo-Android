package com.example.daboyeo_android.data.repository.home

import com.example.daboyeo_android.data.model.home.DetailPostData
import com.example.daboyeo_android.data.model.home.PostListData
import org.json.JSONArray
import retrofit2.Response

interface HomeDataSource {
    suspend fun getTimeLine() : Response<PostListData>
    suspend fun addPost(tags: JSONArray, images: JSONArray, hashMap: HashMap<String, String>): Response<String>
}