package com.example.daboyeo_android.data.model.sign

data class GoogleTokenRequest (
        val grant_type : String,
        val client_id: String,
        val client_secret: String,
        val redirect_uri: String,
        val code: String,
        val id_token: String
        )