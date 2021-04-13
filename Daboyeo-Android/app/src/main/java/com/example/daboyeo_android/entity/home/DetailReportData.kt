package com.example.daboyeo_android.entity.home

import com.google.gson.annotations.SerializedName

data class DetailReportData (
        @SerializedName("report_id") val report_id: String,
        @SerializedName("reporter_name")  val reporter_name: String,
        @SerializedName("reporter_profile_uri") val reporter_profile_uri: String,
        @SerializedName("content") val content: String,
        @SerializedName("tags") val tags: List<String>,
        @SerializedName("image_uris") val image_uris: List<String>,
        @SerializedName("comments") val comments: List<Comment>,
        @SerializedName("num_of_sympathy") val num_of_sympathy : Int,
        @SerializedName("is_sympathy") val is_sympathy: Boolean,
        @SerializedName("created_at") val created_at: String,
        @SerializedName("updated_at") val updated_at: String,
        @SerializedName("location") val location: String
        )

data class Comment(
        @SerializedName("profile_uri") val profile_uri: String,
        @SerializedName("name") val name: String,
        @SerializedName("content") val content: String
        )