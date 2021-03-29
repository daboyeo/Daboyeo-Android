package com.example.daboyeo_android.util

import android.app.Application

class DaboyeoApplication: Application() {
    companion object {
        var pref: SharedPreferencesManager? =null
    }

    override fun onCreate() {
        pref = SharedPreferencesManager(applicationContext)
        super.onCreate()
    }
}