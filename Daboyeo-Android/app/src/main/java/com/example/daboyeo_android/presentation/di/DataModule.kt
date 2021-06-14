package com.example.daboyeo_android.presentation.di

import com.example.daboyeo_android.data.api.service.*
import com.example.daboyeo_android.data.repository.home.*
import com.example.daboyeo_android.data.repository.post.PostDataSource
import com.example.daboyeo_android.data.repository.post.PostDataSourceImpl
import com.example.daboyeo_android.data.repository.profile.ProfileDataSource
import com.example.daboyeo_android.data.repository.profile.ProfileDataSourceImpl
import com.example.daboyeo_android.data.repository.search.*
import com.example.daboyeo_android.data.repository.sign.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideSignDataSource(
            signService: SignService
    ): SignDataSource {
        return SignDataSourceImpl(signService)
    }

    @Singleton
    @Provides
    fun provideSearchDataSource(
            timelineService: TimelineService
    ): SearchDataSource {
        return SearchDataSourceImpl(timelineService)
    }

    @Singleton
    @Provides
    fun provideHomeDataSource(
            timelineService: TimelineService
    ): HomeDataSource {
        return HomeDataSourceImpl(timelineService)
    }

    @Singleton
    @Provides
    fun provideProfileDataSource(
            profileService: ProfileService,
            fileService: FileService
    ): ProfileDataSource {
        return ProfileDataSourceImpl(profileService, fileService)
    }

    @Singleton
    @Provides
    fun providePostDataSource(
            postService: PostService,
            fileService: FileService
    ): PostDataSource {
        return PostDataSourceImpl(postService, fileService)
    }
}
