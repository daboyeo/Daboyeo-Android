package com.example.daboyeo_android.presentation.di.useCaseModule

import com.example.daboyeo_android.domain.repository.SignRepository
import com.example.daboyeo_android.domain.usecase.sign.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SignUseCaseModule {
    @Provides
    @Singleton
    fun provideGetGoogleTokenUseCase(
        signRepository: SignRepository
    ): GetGoogleTokenUseCase {
        return GetGoogleTokenUseCase(signRepository)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        signRepository: SignRepository
    ): LoginUseCase {
        return LoginUseCase(signRepository)
    }
}