package com.example.daboyeo_android.writing.model

data class WritingData (
    val content : String,
    val location: String,
    val tags : List<String>,
    val image_uris : List<String>
        )