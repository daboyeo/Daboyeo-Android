package com.example.daboyeo_android.repository

import android.database.DataSetObservable
import com.example.daboyeo_android.base.BaseRepository
import com.example.daboyeo_android.entity.home.DetailReportData
import com.example.daboyeo_android.http.interceptor.DaboyeoConnect
import com.example.daboyeo_android.http.interceptor.Result
import com.google.gson.JsonObject

class ReportRepository : BaseRepository() {
    private val token = getAccessToken()!!

    suspend fun clickSympathy(reportId: JsonObject) : Result<Boolean> {
        return mappingToResult { DaboyeoConnect.getAPI().sympathy(token, reportId) }
    }

    suspend fun addComment(hashMap: HashMap<String, String>) : Result<Unit> {
        return mappingToResult { DaboyeoConnect.getAPI().addComment(token, hashMap) }
    }

    suspend fun deleteComment(hashMap: HashMap<String, String>) : Result<Unit> {
        return mappingToResult { DaboyeoConnect.getAPI().deleteComment(token, hashMap) }
    }

    suspend fun modifyReport(hashMap: HashMap<String, String>) : Result<Unit> {
        return mappingToResult { DaboyeoConnect.getAPI().modifyReport(token, hashMap) }
    }

    suspend fun deleteReport(reportId: JsonObject) : Result<Unit> {
        return mappingToResult { DaboyeoConnect.getAPI().deleteReport(token, reportId) }
    }

    suspend fun getDetailReport(reportId: Int) :Result<DetailReportData> {
        return mappingToResult { DaboyeoConnect.getAPI().getDetailReport(token,reportId) }
    }
}