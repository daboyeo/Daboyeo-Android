package com.example.daboyeo_android.repository

import com.example.daboyeo_android.base.BaseRepository
import com.example.daboyeo_android.entity.home.ReportListData
import com.example.daboyeo_android.entity.profile.ProfileData
import com.example.daboyeo_android.http.interceptor.DaboyeoConnect
import com.example.daboyeo_android.http.interceptor.Result

class ProfileRepository : BaseRepository(){
    private val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTYyMDI3NjIyNiwianRpIjoiZGUxYjQ3ZDItNmZmZi00MzNkLTkxNzMtYzU2M2M2NmQwZmI4IiwibmJmIjoxNjIwMjc2MjI2LCJ0eXBlIjoiYWNjZXNzIiwic3ViIjoiZ29vZ2xlQDExNDAyMTg5OTU3NTA5Nzc4NTU2MSIsImV4cCI6MTYyMTQ4NTgyNn0.9T7bu48-VW-fmOcjEPpDjoMwHYd-6TMwTYpxto5HBd8"

    suspend fun getMyProfile() : Result<ProfileData> {
        return mappingToResult { DaboyeoConnect.getAPI().getMyProfile(token) }
    }

    suspend fun getUserProfile(userName : String) : Result<ProfileData> {
        return mappingToResult { DaboyeoConnect.getAPI().getUserProfile(userName) }
    }

    suspend fun getProfileReports(userName: String) : Result<ReportListData> {
        return mappingToResult { DaboyeoConnect.getAPI().getProfileReports(token, userName) }
    }

    suspend fun modifyProfile(hashMap: HashMap<String, String>) : Result<Unit> {
        return mappingToResult { DaboyeoConnect.getAPI().modifyProfile(token, hashMap) }
    }
}