package com.example.daboyeo_android.http.service

import com.example.daboyeo_android.entity.profile.ProfileData
import retrofit2.Response
import retrofit2.http.*

interface ProfileService : DaboyeoService{

    @GET("/user")
    suspend fun getMyProfile(
        @Header("Authorization") header: String
    ): Response<ProfileData>

    @GET("/user")
    suspend fun getUserProfile(
        @Header("Authorization") header: String,
        @Query("user") user: String
    ): Response<ProfileData>

    @PUT("/user")
    suspend fun modifyProfile(
        @Header("Authorization") header: String,
        @Body body: HashMap<String, String>
    ): Response<Unit>

}