package com.example.daboyeo_android.repository

import com.example.daboyeo_android.base.BaseRepository
import com.example.daboyeo_android.http.interceptor.DaboyeoConnect
import com.example.daboyeo_android.http.interceptor.Result
import java.util.*

class SignRepository : BaseRepository(){
    suspend fun signAuth(hashMap: HashMap<String, String>) : Result<String> {
        return mappingToResult { DaboyeoConnect.getAPI().signAuth(hashMap) }
    }
}