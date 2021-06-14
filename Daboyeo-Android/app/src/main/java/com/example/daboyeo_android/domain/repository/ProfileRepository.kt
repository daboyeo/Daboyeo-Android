package com.example.daboyeo_android.domain.repository

import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.data.model.profile.ProfileData
import com.example.daboyeo_android.data.api.interceptor.Result
import okhttp3.MultipartBody

interface ProfileRepository{
    suspend fun getMyProfile() : Result<ProfileData>
    suspend fun getUserProfile(userName : String) : Result<ProfileData>
    suspend fun getProfilePosts(userName: String) : Result<PostListData>
    suspend fun modifyProfile(hashMap: HashMap<String, String>) : Result<Unit>
    suspend fun uploadImage(image: MultipartBody.Part) : Result<String>
}