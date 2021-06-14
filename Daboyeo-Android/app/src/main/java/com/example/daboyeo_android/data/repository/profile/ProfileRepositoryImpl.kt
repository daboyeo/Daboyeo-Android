package com.example.daboyeo_android.data.repository.profile

import com.example.daboyeo_android.data.api.interceptor.Result
import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.data.model.profile.ProfileData
import com.example.daboyeo_android.domain.repository.ProfileRepository
import okhttp3.MultipartBody
import retrofit2.Response

class ProfileRepositoryImpl(
        private val profileDataSource: ProfileDataSource,
): ProfileRepository {
    override suspend fun getMyProfile(): Result<ProfileData> {
        return responseToProfileData(profileDataSource.getMyProfile())
    }

    override suspend fun getUserProfile(userName: String): Result<ProfileData> {
        return responseToProfileData(profileDataSource.getUserProfile(userName))
    }

    override suspend fun getProfilePosts(userName: String): Result<PostListData> {
        return responseToProfilePosts(profileDataSource.getProfilePosts(userName))
    }

    override suspend fun modifyProfile(hashMap: HashMap<String, String>): Result<Unit> {
        return responseToModifyProfile(profileDataSource.modifyProfile(hashMap))
    }

    override suspend fun uploadImage(image: MultipartBody.Part): Result<String> {
        return responseToUploadImage(profileDataSource.uploadImage(image))
    }

    private fun responseToProfileData(response: Response<ProfileData>): Result<ProfileData> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }

    private fun responseToProfilePosts(response: Response<PostListData>) : Result<PostListData> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }

    private fun responseToModifyProfile(response: Response<Unit>) : Result<Unit> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }

    private fun responseToUploadImage(response: Response<String>) : Result<String> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it)
            }
        }
        return Result.Error(response.code())
    }
}