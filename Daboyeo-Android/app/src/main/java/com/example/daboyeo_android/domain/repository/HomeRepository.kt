package com.example.daboyeo_android.domain.repository

import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.data.api.interceptor.Result
import com.example.daboyeo_android.data.model.home.DetailPostData
import org.json.JSONArray

interface HomeRepository{
    suspend fun getTimeLine() : Result<PostListData>
    suspend fun addPost(tags: JSONArray, images: JSONArray, hashMap: HashMap<String, String>): Result<String>
}
