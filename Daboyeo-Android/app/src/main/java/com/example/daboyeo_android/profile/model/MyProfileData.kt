package com.example.daboyeo_android.profile.model

import com.google.gson.annotations.SerializedName

data class MyProfileData (
        @SerializedName("name") val name: String,
        @SerializedName("profile_uri") val profile_uri: String
        )