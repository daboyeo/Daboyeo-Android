package com.example.daboyeo_android.data.api.service

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import java.io.File

interface FileService {
    @GET("/file")
    suspend fun getFile(
        @Query("uuid") uuid: String
    ): Response<File>

    @Multipart
    @POST("file")
    suspend fun uploadFile(
        @Part file : MultipartBody.Part
    ) : Response<String>
}