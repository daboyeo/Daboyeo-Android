package com.example.daboyeo_android.data.model.home

data class DetailPostData (
    val report_id: Int,
    val reporter_name: String,
    val reporter_profile_uri: String,
    val content: String,
    val tags: List<String>,
    val image_uris: List<String>,
    val comments: List<CommentData>,
    val num_of_sympathy : Int,
    val is_sympathy: Boolean,
    val created_at: String,
    val updated_at: String,
    val location: String
        )

data class CommentData(
        val comment_id: Int,
        val profile_uri: String,
        val name: String,
        val content: String,
        val user_id: String
        )