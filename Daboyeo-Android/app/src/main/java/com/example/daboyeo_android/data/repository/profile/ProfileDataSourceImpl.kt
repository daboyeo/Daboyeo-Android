package com.example.daboyeo_android.data.repository.profile

import com.example.daboyeo_android.data.api.service.FileService
import com.example.daboyeo_android.data.api.service.ProfileService
import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.data.model.profile.ProfileData
import com.example.daboyeo_android.presentation.util.DaboyeoApplication
import okhttp3.MultipartBody
import retrofit2.Response

class ProfileDataSourceImpl (
    private val profileService: ProfileService,
    private val fileService: FileService
): ProfileDataSource {
    private val token = DaboyeoApplication.pref!!.getToken()

    override suspend fun getMyProfile(): Response<ProfileData> {
        return profileService.getMyProfile(token)
    }

    override suspend fun getUserProfile(userName: String): Response<ProfileData> {
        return profileService.getUserProfile(token, userName)
    }

    override suspend fun getProfilePosts(userName: String): Response<PostListData> {
        return profileService.getProfilePosts(token, userName)
    }

    override suspend fun modifyProfile(hashMap: HashMap<String, String>): Response<Unit> {
        return profileService.modifyProfile(token, hashMap)
    }

    override suspend fun uploadImage(image: MultipartBody.Part): Response<String> {
        return fileService.uploadFile(image)
    }

}