package com.example.daboyeo_android.presentation.di

import com.example.daboyeo_android.domain.usecase.home.GetTimeLineUseCase
import com.example.daboyeo_android.domain.usecase.post.*
import com.example.daboyeo_android.domain.usecase.profile.*
import com.example.daboyeo_android.domain.usecase.search.SearchUseCase
import com.example.daboyeo_android.domain.usecase.sign.GetGoogleTokenUseCase
import com.example.daboyeo_android.domain.usecase.sign.LoginUseCase
import com.example.daboyeo_android.presentation.viewModel.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FactoryModule {

    @Singleton
    @Provides
    fun provideSignViewModelFactory(
        loginUseCase: LoginUseCase,
        getGoogleTokenUseCase: GetGoogleTokenUseCase
    ): SignViewModelFactory {
        return SignViewModelFactory(
            loginUseCase,
            getGoogleTokenUseCase
        )
    }

    @Singleton
    @Provides
    fun provideSearchViewModelFactory(
        searchUseCase: SearchUseCase
    ): SearchViewModelFactory{
        return SearchViewModelFactory(
            searchUseCase
        )
    }

    @Singleton
    @Provides
    fun provideProfileViewModelFactory(
        getMyProfileInfoUseCase: GetMyProfileInfoUseCase,
        getProfileInfoUseCase: GetProfileInfoUseCase,
        getProfilePostsUseCase: GetProfilePostsUseCase,
        modifyProfileUseCase: ModifyProfileUseCase,
        uploadImageUseCase: UploadImageUseCase
    ) : ProfileViewModelFactory {
        return ProfileViewModelFactory(
            getProfileInfoUseCase,
            getMyProfileInfoUseCase,
            getProfilePostsUseCase,
            uploadImageUseCase,
            modifyProfileUseCase
        )
    }

    @Singleton
    @Provides
    fun provideHomeViewModelFactor(
            getTimeLineUseCase: GetTimeLineUseCase,
    ): HomeViewModelFactory {
        return HomeViewModelFactory(
                getTimeLineUseCase
        )
    }

    @Singleton
    @Provides
    fun providePostViewModelFactor(
            addCommentUseCase: AddCommentUseCase,
            deleteCommentUseCase: DeleteCommentUseCase,
            deletePostUseCase: DeletePostUseCase,
            getDetailPostUseCase: GetDetailPostUseCase,
            getImageUseCase: GetImageUseCase,
            likeUseCase: LikeUseCase
    ): PostViewModelFactory {
        return PostViewModelFactory(
                addCommentUseCase,
                deleteCommentUseCase,
                deletePostUseCase,
                getDetailPostUseCase,
                getImageUseCase,
                likeUseCase
        )
    }

    @Singleton
    @Provides
    fun provideAddPostViewModelFactory(
            addPostUseCase: AddPostUseCase,
            uploadImageUseCase: UploadImageUseCase
    ): AddPostViewModelFactory {
        return AddPostViewModelFactory(
                addPostUseCase,
                uploadImageUseCase
        )
    }

}