package com.example.daboyeo_android.http.service

import com.example.daboyeo_android.entity.home.DetailReportData
import com.example.daboyeo_android.entity.home.ReportListData
import org.json.JSONArray
import retrofit2.Response
import retrofit2.http.*

interface TimelineService {
    @GET("report")
    suspend fun getTimeLine(
            @Header("Authorization") header: String
    ): Response<ReportListData>

    @GET("report")
    suspend fun getTagTimeLine(
        @Header("Authorization") header: String,
        @Query("tag") tag: String
    ): Response<ReportListData>

    @GET("report")
    suspend fun getSearchTimeLine(
        @Header("Authorization") header: String,
        @Query("search") search: String,
    ): Response<ReportListData>

    @GET("report/{id}")
    suspend fun getDetailReport(
            @Header("Authorization") header: String,
            @Path("id") id: Int
    ): Response<DetailReportData>

    @POST("report")
    suspend fun writingReport(
        @Header("Authorization") header: String,
        @Body tags: JSONArray,
        @Body image_uris: JSONArray,
        @Body hashMap: HashMap<String, String>
    ): Response<String>

    @POST("report")
    suspend fun writing(
        @Header("Authorization") header: String,
        @Body tags : HashMap<String, String>,
        @Body hashMap: HashMap<String, String>
    ): Response<String>

}