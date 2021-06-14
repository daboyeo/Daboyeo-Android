package com.example.daboyeo_android.presentation.di

import com.example.daboyeo_android.presentation.view.home.adapter.PostsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AdapterModule {

    @Provides
    @Singleton
    fun providePostAdapter() : PostsAdapter{
        return PostsAdapter()
    }

}