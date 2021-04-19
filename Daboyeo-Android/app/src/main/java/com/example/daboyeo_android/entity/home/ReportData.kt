package com.example.daboyeo_android.entity.home

data class ReportData(
        val report_id: String,
        val reporter_name: String,
        val reporter_profile_uri: String,
        val content: String,
        val tags: List<String>,
        val image_uris: List<String>,
        val num_of_sympathy: Int,
        val is_sympathy: Boolean,
        val created_at: String,
        val updated_at: String,
        val location: String
)