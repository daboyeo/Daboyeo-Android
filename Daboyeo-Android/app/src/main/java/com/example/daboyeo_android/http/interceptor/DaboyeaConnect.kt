package com.example.daboyeo_android.http.interceptor

import com.example.daboyeo_android.http.service.DaboyeoService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object DaboyeaConnect {
    private var retrofit: Retrofit
    private var api: DaboyeoService
    private const val baseURL = "https://6l8nidp3k7.execute-api.ap-northeast-2.amazonaws.com/dev/"
    const val s3 = ""

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

        api = retrofit.create(DaboyeoService::class.java)
    }

    fun getAPI() = api
}