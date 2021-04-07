package com.example.daboyeo_android.home.model

import com.google.gson.annotations.SerializedName

data class ReportsData (
        @SerializedName("reports") val reports: List<ReportData>
        )

