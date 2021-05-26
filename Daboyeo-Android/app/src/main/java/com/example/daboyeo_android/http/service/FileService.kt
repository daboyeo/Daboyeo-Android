package com.example.daboyeo_android.http.service

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface FileService {
    @GET("/file")
    suspend fun getFile(
        @Query("uuid") uuid: String
    ): Response<Unit>

    @Multipart
    @POST("file")
    suspend fun uploadFile(
        @Part file : MultipartBody.Part
    ) : Response<Int>
}