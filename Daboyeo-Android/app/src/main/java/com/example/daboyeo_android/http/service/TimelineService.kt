package com.example.daboyeo_android.http.service

import com.example.daboyeo_android.entity.home.DetailReportData
import com.example.daboyeo_android.entity.home.ReportData
import com.example.daboyeo_android.entity.profile.MyProfileData
import retrofit2.Response
import retrofit2.http.*

interface TimelineService : DaboyeoService {
    @GET("/report")
    suspend fun getTimeLine(
            @Header("Authorization") header: String
    ): Response<List<ReportData>>

    @GET("/report/{id}")
    suspend fun detailReport(
            @Header("Authorization") header: String
    ): Response<DetailReportData>

    @POST("/comment")
    suspend fun comment(
            @Header("Authorization") header: String,
            @Body body: HashMap<String, String>
    ): Response<Unit>

    @PUT("/sympathy")
    suspend fun sympathy(
            @Header("Authorization") header: String,
            @Body report_id: String
    ): Response<Boolean>
}