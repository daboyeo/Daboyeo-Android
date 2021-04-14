package com.example.daboyeo_android.http.service

import com.example.daboyeo_android.entity.profile.MyProfileData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface ProfileService : DaboyeoService{
    @GET("/user")
    suspend fun getMyProfile(
        @Header("Authorization") header: String
    ): Response<MyProfileData>

    @PUT("/user")
    suspend fun modifyProfile(
        @Header("Authorization") header: String,
        @Body body: HashMap<String, String>
    ): Response<Unit>
}