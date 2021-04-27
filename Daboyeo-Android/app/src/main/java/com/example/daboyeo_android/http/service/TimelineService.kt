package com.example.daboyeo_android.http.service

import com.example.daboyeo_android.entity.home.DetailReportData
import com.example.daboyeo_android.entity.home.ReportData
import retrofit2.Response
import retrofit2.http.*

interface TimelineService {
    @GET("/report")
    suspend fun getTimeLine(
            @Header("Authorization") header: String,
            @Query("user") user: String,
            @Query("search") search: String,
            @Query("tag") tag: String
    ): Response<List<ReportData>>

    @GET("/report/{id}")
    suspend fun detailReport(
            @Header("Authorization") header: String
    ): Response<DetailReportData>

}