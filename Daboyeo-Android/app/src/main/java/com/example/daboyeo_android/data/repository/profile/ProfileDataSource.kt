package com.example.daboyeo_android.data.repository.profile

import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.data.model.profile.ProfileData
import okhttp3.MultipartBody
import retrofit2.Response

interface ProfileDataSource {
    suspend fun getMyProfile() : Response<ProfileData>
    suspend fun getUserProfile(userName : String) : Response<ProfileData>
    suspend fun getProfilePosts(userName: String) : Response<PostListData>
    suspend fun modifyProfile(hashMap: HashMap<String, String>) : Response<Unit>
    suspend fun uploadImage(image: MultipartBody.Part) : Response<String>
}