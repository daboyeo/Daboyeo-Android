package com.example.daboyeo_android.data.api.service

import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.data.model.profile.ProfileData
import retrofit2.Response
import retrofit2.http.*

interface ProfileService {

    @GET("user")
    suspend fun getMyProfile(
            @Header("Authorization") header: String
    ): Response<ProfileData>

    @GET("user")
    suspend fun getUserProfile(
            @Header("Authorization") header: String,
            @Query("user") user: String
    ): Response<ProfileData>

    @PUT("user")
    suspend fun modifyProfile(
            @Header("Authorization") header: String,
            @Body body: HashMap<String, String>
    ): Response<Unit>

    @GET("report")
    suspend fun getProfilePosts(
            @Header("Authorization") header: String,
            @Query("user") user: String
    ): Response<PostListData>
}