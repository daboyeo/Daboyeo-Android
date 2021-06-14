package com.example.daboyeo_android.presentation.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DaboyeoApplication: Application() {
    companion object {
        var pref: SharedPreferencesManager? =null
    }

    override fun onCreate() {
        pref = SharedPreferencesManager(applicationContext)
        super.onCreate()
    }
}