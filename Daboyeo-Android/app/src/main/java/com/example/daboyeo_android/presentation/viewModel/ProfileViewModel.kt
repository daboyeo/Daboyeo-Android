package com.example.daboyeo_android.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.data.model.home.PostListData
import com.example.daboyeo_android.data.model.profile.ProfileData
import com.example.daboyeo_android.data.api.interceptor.Result
import com.example.daboyeo_android.domain.repository.ProfileRepository
import com.example.daboyeo_android.domain.usecase.profile.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.lang.Exception

class ProfileViewModel(
    private val getProfileInfoUseCase: GetProfileInfoUseCase,
    private val getMyProfileInfoUseCase: GetMyProfileInfoUseCase,
    private val getProfilePostsUseCase: GetProfilePostsUseCase,
    private val uploadImageUseCase: UploadImageUseCase,
    private val modifyProfileUseCase: ModifyProfileUseCase
) : ViewModel() {
    private val _profilePosts = MutableLiveData<PostListData>()
    val profilePosts: LiveData<PostListData> get() = _profilePosts
    private val _profileData = MutableLiveData<ProfileData>()
    val profileData: LiveData<ProfileData> get() = _profileData

    fun getMyProfile() {
        viewModelScope.launch {
            val result = getMyProfileInfoUseCase.execute()
            _profileData.value = result.data
            getProfilePosts(result.data!!.id)
        }
    }

    fun getUserProfile(userName: String) {
        viewModelScope.launch {
            val result = getProfileInfoUseCase.execute(userName)
            _profileData.value = result.data
            getProfilePosts(result.data!!.id)
        }
    }

    private fun getProfilePosts(id: String) {
        viewModelScope.launch {
            val result = getProfilePostsUseCase.execute(id)
            _profilePosts.value = result.data
        }
    }

    fun modifyProfile(name: String, file: File) {
        var hashMap = HashMap<String, String>()
        hashMap["name"] = name
        hashMap["profile_uri"] = imageToServer(file)

        viewModelScope.launch {
            val result = modifyProfileUseCase.execute(hashMap)
            if(result.code == 200) {
                getMyProfile()
            }
        }
    }

    private fun imageToServer(file: File) : String{
        val image = fileToMultipart(file)
        var imageUri = " "
        viewModelScope.launch {
            try {
                val result = uploadImageUseCase.execute(image)
                imageUri = result.data.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return imageUri
    }

    private fun fileToMultipart(file: File): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            "image",
            file.name,
            file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        )
    }
}