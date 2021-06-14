package com.example.daboyeo_android.presentation.di

import com.example.daboyeo_android.data.repository.home.HomeDataSource
import com.example.daboyeo_android.data.repository.home.HomeRepositoryImpl
import com.example.daboyeo_android.data.repository.post.PostDataSource
import com.example.daboyeo_android.data.repository.post.PostRepositoryImpl
import com.example.daboyeo_android.data.repository.profile.ProfileDataSource
import com.example.daboyeo_android.data.repository.profile.ProfileDataSourceImpl
import com.example.daboyeo_android.data.repository.profile.ProfileRepositoryImpl
import com.example.daboyeo_android.data.repository.search.*
import com.example.daboyeo_android.data.repository.sign.*
import com.example.daboyeo_android.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideSignRepository(
        signDataSource: SignDataSource
    ): SignRepository {
        return SignRepositoryImpl(signDataSource)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(
        searchDataSource: SearchDataSource
    ): SearchRepository {
        return SearchRepositoryImpl(searchDataSource)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        homeDataSource: HomeDataSource
    ): HomeRepository {
        return HomeRepositoryImpl(homeDataSource)
    }

    @Singleton
    @Provides
    fun provideProfileRepository(
        profileDataSource: ProfileDataSource
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileDataSource)
    }

    @Singleton
    @Provides
    fun providePostRepository(
            postDataSource: PostDataSource
    ): PostRepository {
        return PostRepositoryImpl(postDataSource)
    }
}