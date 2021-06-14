package com.example.daboyeo_android.presentation.di

import com.example.daboyeo_android.data.api.service.DaboyeoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    private val baseURL = "https://6l8nidp3k7.execute-api.ap-northeast-2.amazonaws.com/dev/"
    private val fileServer = "http://54.180.105.66:8000/"
    private val googleTokenServer = "https://www.googleapis.com/"

    @Singleton
    @Provides
    fun provideBaseRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideFileRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(fileServer)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGoogleRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(googleTokenServer)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit):DaboyeoService{
        return retrofit.create(DaboyeoService::class.java)
    }

}