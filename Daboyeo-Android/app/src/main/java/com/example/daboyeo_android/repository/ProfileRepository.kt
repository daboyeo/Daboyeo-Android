package com.example.daboyeo_android.repository

import com.example.daboyeo_android.base.BaseRepository
import com.example.daboyeo_android.entity.home.ReportListData
import com.example.daboyeo_android.entity.profile.ProfileData
import com.example.daboyeo_android.http.interceptor.DaboyeaConnect
import com.example.daboyeo_android.http.interceptor.Result

class ProfileRepository : BaseRepository(){
    private val token = getAccessToken()!!

    suspend fun getMyProfile() : Result<ProfileData> {
        return mappingToResult { DaboyeaConnect.getAPI().getMyProfile(token) }
    }

    suspend fun getUserProfile(userName : String) : Result<ProfileData> {
        return mappingToResult { DaboyeaConnect.getAPI().getUserProfile(userName) }
    }

    suspend fun getProfileReports(userName: String) : Result<ReportListData> {
        return mappingToResult { DaboyeaConnect.getAPI().getProfileReports(token, userName) }
    }

    suspend fun modifyProfile(hashMap: HashMap<String, String>) : Result<Unit> {
        return mappingToResult { DaboyeaConnect.getAPI().modifyProfile(token, hashMap) }
    }
}