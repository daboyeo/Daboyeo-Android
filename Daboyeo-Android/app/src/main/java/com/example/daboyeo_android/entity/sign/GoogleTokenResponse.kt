package com.example.daboyeo_android.entity.sign

data class GoogleTokenResponse (
    val access_token : String,
    val expires_in: Int,
    val scope: String,
    val token_type : String,
    val id_token: String
    )