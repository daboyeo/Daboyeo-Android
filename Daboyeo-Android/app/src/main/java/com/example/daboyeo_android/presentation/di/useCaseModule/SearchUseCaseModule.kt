package com.example.daboyeo_android.presentation.di.useCaseModule

import com.example.daboyeo_android.domain.repository.SearchRepository
import com.example.daboyeo_android.domain.usecase.search.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
class SearchUseCaseModule {
    @Provides
    @Singleton
    fun provideSearchUseCase(
        searchRepository: SearchRepository
    ): SearchUseCase {
        return SearchUseCase(searchRepository)
    }
}