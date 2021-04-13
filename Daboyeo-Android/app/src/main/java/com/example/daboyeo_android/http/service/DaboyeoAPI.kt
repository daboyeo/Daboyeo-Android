package com.example.daboyeo_android.http.service

import com.example.daboyeo_android.entity.home.ReportData
import com.example.daboyeo_android.entity.profile.MyProfileData
import retrofit2.Response
import retrofit2.http.*

interface DaboyeoAPI {

    @POST("/auth")
    suspend fun signAuth(
            @Body body: HashMap<String, String>
    ): Response<String>

    @GET("/user")
    suspend fun getMyProfile(
            @Header("Authorization") header: String
    ): Response<MyProfileData>

    @PUT("/user")
    suspend fun modifyProfile(
            @Header("Authorization") header: String,
            @Body body: HashMap<String, String>
    ): Response<Unit>

    @GET("/report")
    suspend fun getTimeLine(
            @Header("Authorization") header: String
    ): Response<List<ReportData>>

    @GET("/report/{id}")
    suspend fun detailReport(
            @Header("Authorization") header: String
    )

    @POST("/comment")
    suspend fun comment(
            @Header("Authorization") header: String,
            @Body body: HashMap<String, String>
    ): Response<Unit>

    @PUT("/sympathy")
    suspend fun sympathy(
            @Header("Authorization") header: String,
            @Body report_id: String
    ): Response<Boolean>

    @POST("/report")
    suspend fun writing(
            @Header("Authorization") header: String,
            @Body tags: List<String>,
            @Body image_uris: List<String>,
            @Body content: String,
            @Body location: String
    ) : Response<String>
}