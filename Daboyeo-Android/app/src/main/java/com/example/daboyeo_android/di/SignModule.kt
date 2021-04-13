package com.example.daboyeo_android.di

import com.example.daboyeo_android.repository.SignRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SignModule {
    @Singleton
    @Provides
    fun loginRepository() = SignRepository()
}