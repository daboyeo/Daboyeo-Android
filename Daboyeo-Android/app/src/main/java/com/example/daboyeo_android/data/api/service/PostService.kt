package com.example.daboyeo_android.data.api.service

import com.example.daboyeo_android.data.model.home.DetailPostData
import com.google.gson.JsonObject
import org.json.JSONArray
import retrofit2.Response
import retrofit2.http.*

interface PostService {
    @POST("comment")
    suspend fun addComment(
        @Header("Authorization") header: String,
        @Body body: HashMap<String, String>
    ): Response<Unit>

    @PUT("sympathy")
    suspend fun like(
        @Header("Authorization") header: String,
        @Body report_id: HashMap<String, Int>
    ): Response<Boolean>

    @DELETE("report")
    suspend fun deletePost (
        @Header("Authorization") header: String,
        @Body report_id: JsonObject
    ): Response<Unit>

    @DELETE("comment")
    suspend fun deleteComment (
        @Header ("Authorization") header: String,
        @Body body: HashMap<String, String>
    ) : Response<Unit>

    @GET("report/{id}")
    suspend fun getDetailPost(
            @Header("Authorization") header: String,
            @Path("id") id: Int
    ): Response<DetailPostData>
}