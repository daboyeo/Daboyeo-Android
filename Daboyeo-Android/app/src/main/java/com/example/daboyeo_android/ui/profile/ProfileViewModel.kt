package com.example.daboyeo_android.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daboyeo_android.entity.home.ReportData
import com.example.daboyeo_android.entity.home.ReportListData
import com.example.daboyeo_android.entity.profile.ProfileData
import com.example.daboyeo_android.http.interceptor.Result
import com.example.daboyeo_android.repository.ProfileRepository
import com.example.daboyeo_android.ui.home.adapter.ReportsAdapter
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val repository = ProfileRepository()
    val mAdapter = ReportsAdapter()
    private val TAG = "ProfileViewModel"

    private val _profileReports = MutableLiveData<List<ReportData>>()
    val profileReports: LiveData<List<ReportData>> get() = _profileReports
    private val _profileData = MutableLiveData<ProfileData>()
    val profileData: LiveData<ProfileData> get() = _profileData

    fun getMyProfile() {
        viewModelScope.launch {
            when(val result = repository.getMyProfile()) {
                is Result.Success -> {
                    if(result.code == 200) {
                        _profileData.value = result.data
                    }
                }
                is Result.Error -> {
                    Log.e(TAG, "getMyProfile fail")
                }
            }
        }
    }

    fun getUserProfile(userName: String) {
        viewModelScope.launch {
            when(val result = repository.getUserProfile(userName)) {
                is Result.Success -> {
                    if(result.code == 200) {
                        _profileData.value = result.data
                    }
                }
                is Result.Error -> {
                    Log.e(TAG, "getUserProfile fail")
                }
            }
        }
    }

    fun getProfileReports(userName: String) {
        viewModelScope.launch {
            when(val result = repository.getProfileReports(userName)) {
                is Result.Success -> {
                    if(result.code == 200) {
                        _profileReports.value = result.data.reports
                        mAdapter.setList(profileReports.value!!)
                    }
                }
                is Result.Error -> {
                    Log.e(TAG, "getProfileReports fail")
                }
            }
        }
    }


    fun modifyProfile(name: String, profile_uri: String) {
        var hashMap = HashMap<String, String>()
        hashMap["name"] = name
        hashMap["profile_uri"] = profile_uri

        viewModelScope.launch {
            when(val result = repository.modifyProfile(hashMap)) {
                is Result.Success -> {
                    if(result.code == 200) {
                        getMyProfile()
                    }
                }
                is Result.Error -> {
                    Log.e(TAG, "getProfileReports fail")
                }
            }
        }
    }

}