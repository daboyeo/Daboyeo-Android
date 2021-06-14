package com.example.daboyeo_android.data.api.service

import com.example.daboyeo_android.data.model.home.DetailPostData
import com.example.daboyeo_android.data.model.home.PostListData
import org.json.JSONArray
import retrofit2.Response
import retrofit2.http.*

interface TimelineService {
    @GET("report")
    suspend fun getTimeLine(
            @Header("Authorization") header: String
    ): Response<PostListData>

    @GET("report")
    suspend fun getSearchTimeLine(
        @Header("Authorization") header: String,
        @Query("search") search: String,
    ): Response<PostListData>

    @GET("report/{id}")
    suspend fun getDetailPost(
            @Header("Authorization") header: String,
            @Path("id") id: Int
    ): Response<DetailPostData>

    @POST("report")
    suspend fun addPost(
            @Header("Authorization") header: String,
            @Body tags: JSONArray,
            @Body image_uris: JSONArray,
            @Body hashMap: HashMap<String, String>
    ): Response<String>

}