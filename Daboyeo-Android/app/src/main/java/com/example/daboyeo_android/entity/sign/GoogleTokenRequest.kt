package com.example.daboyeo_android.entity.sign

data class GoogleTokenRequest (
        val grant_type : String,
        val client_id: String,
        val client_secret: String,
        val redirect_uri: String,
        val code: String,
        val id_token: String
        )