package com.example.daboyeo_android.entity.home

import com.google.gson.annotations.SerializedName

data class ReportData(
        @SerializedName("report_id") val report_id: String,
        @SerializedName("reporter_name")  val reporter_name: String,
        @SerializedName("reporter_profile_uri") val reporter_profile_uri: String,
        @SerializedName("content") val content: String,
        @SerializedName("tags") val tags: List<String>,
        @SerializedName("image_uris") val image_uris: List<String>,
        @SerializedName("num_of_sympathy") val num_of_sympathy : Int,
        @SerializedName("is_sympathy") val is_sympathy: Boolean,
        @SerializedName("created_at") val created_at: String,
        @SerializedName("updated_at") val updated_at: String,
        @SerializedName("location") val location: String
)