package com.example.daboyeo_android.http.interceptor

import com.example.daboyeo_android.http.service.DaboyeoService
import com.example.daboyeo_android.http.service.FileService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DaboyeoConnect {
    private var retrofit: Retrofit
    private var fileRetrofit: Retrofit
    private var api: DaboyeoService
    private var fileApi : FileService
    private const val baseURL = "https://6l8nidp3k7.execute-api.ap-northeast-2.amazonaws.com/dev/"
    private const val fileServer = "http://54.180.105.66:8000/"

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fileRetrofit = Retrofit.Builder()
            .baseUrl(fileServer)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(DaboyeoService::class.java)
        fileApi = fileRetrofit.create(FileService::class.java)
    }

    fun getAPI() = api

    fun getFileAPI() = fileApi
}