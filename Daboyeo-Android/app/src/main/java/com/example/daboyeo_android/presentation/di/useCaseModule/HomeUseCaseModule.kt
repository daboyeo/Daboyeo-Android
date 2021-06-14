package com.example.daboyeo_android.presentation.di.useCaseModule

import com.example.daboyeo_android.domain.repository.HomeRepository
import com.example.daboyeo_android.domain.usecase.home.GetTimeLineUseCase
import com.example.daboyeo_android.domain.usecase.post.GetDetailPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HomeUseCaseModule {
    @Provides
    @Singleton
    fun provideGetTimeLineUseCase(
        homeRepository: HomeRepository
    ): GetTimeLineUseCase {
        return GetTimeLineUseCase(homeRepository)
    }

}