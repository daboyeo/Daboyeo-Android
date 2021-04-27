package com.example.daboyeo_android.repository

import com.example.daboyeo_android.base.BaseRepository
import com.example.daboyeo_android.http.interceptor.DaboyeaConnect
import com.example.daboyeo_android.http.interceptor.Result

class ReportRepository : BaseRepository() {
    private val token = getAccessToken()!!

    suspend fun addComment(hashMap: HashMap<String, String>) : Result<Unit> {
        return mappingToResult { DaboyeaConnect.getAPI().addComment(token, hashMap) }
    }

    suspend fun deleteComment(hashMap: HashMap<String, String>) : Result<Unit> {
        return mappingToResult { DaboyeaConnect.getAPI().deleteComment(token, hashMap) }
    }

    suspend fun modifyReport(hashMap: HashMap<String, String>) : Result<Unit> {
        return mappingToResult { DaboyeaConnect.getAPI().modifyReport(token, hashMap) }
    }
}