package com.example.daboyeo_android.http.service

import retrofit2.Response
import retrofit2.http.*

interface ReportService {
    @POST("/comment")
    suspend fun addComment(
        @Header("Authorization") header: String,
        @Body body: HashMap<String, String>
    ): Response<Unit>

    @PUT("/sympathy")
    suspend fun sympathy(
        @Header("Authorization") header: String,
        @Body report_id: String
    ): Response<Boolean>

    @PUT("/report")
    suspend fun modifyReport (
        @Header("Authorization") header: String,
        @Body body: HashMap<String, String>
    ): Response<Unit>

    @DELETE("/report")
    suspend fun deleteReport (
        @Header("Authorization") header: String,
        @Body body: HashMap<String, String>
    ): Response<Unit>

    @DELETE("/comment")
    suspend fun deleteComment (
        @Header ("Authorization") header: String,
        @Body body: HashMap<String, String>
    ) : Response<Unit>
}