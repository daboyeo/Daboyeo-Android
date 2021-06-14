package com.example.daboyeo_android.presentation.di.useCaseModule

import com.example.daboyeo_android.domain.repository.ProfileRepository
import com.example.daboyeo_android.domain.usecase.profile.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProfileUseCaseModule {
    @Provides
    @Singleton
    fun provideGetMyProfileInfoUseCase(
        profileRepository: ProfileRepository
    ): GetMyProfileInfoUseCase {
        return GetMyProfileInfoUseCase(profileRepository)
    }
    
    @Provides
    @Singleton
    fun provideGetProfileInfoUseCase(
        profileRepository: ProfileRepository
    ): GetProfileInfoUseCase {
        return GetProfileInfoUseCase(profileRepository)
    }
    
    @Provides
    @Singleton
    fun provideGetMyProfilePostsUseCase(
        profileRepository: ProfileRepository
    ): GetProfilePostsUseCase {
        return GetProfilePostsUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideModifyProfileUseCase(
        profileRepository: ProfileRepository
    ): ModifyProfileUseCase {
        return ModifyProfileUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideUploadImageUseCase(
        profileRepository: ProfileRepository
    ): UploadImageUseCase {
        return UploadImageUseCase(profileRepository)
    }

}