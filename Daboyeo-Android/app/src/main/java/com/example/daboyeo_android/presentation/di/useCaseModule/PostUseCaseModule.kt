package com.example.daboyeo_android.presentation.di.useCaseModule

import com.example.daboyeo_android.domain.repository.AddPostRepository
import com.example.daboyeo_android.domain.repository.HomeRepository
import com.example.daboyeo_android.domain.repository.PostRepository
import com.example.daboyeo_android.domain.usecase.post.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PostUseCaseModule {
    @Singleton
    @Provides
    fun provideAddCommentUseCase(
        postRepository: PostRepository
    ): AddCommentUseCase {
        return AddCommentUseCase(postRepository)
    }

    @Singleton
    @Provides
    fun provideAddPostUseCase(
        addPostRepository: AddPostRepository
    ): AddPostUseCase {
        return AddPostUseCase(addPostRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteCommentUseCase(
        postRepository: PostRepository
    ): DeleteCommentUseCase {
        return DeleteCommentUseCase(postRepository)
    }

    @Singleton
    @Provides
    fun provideDeletePostUseCase(
        postRepository: PostRepository
    ): DeletePostUseCase {
        return DeletePostUseCase(postRepository)
    }

    @Singleton
    @Provides
    fun provideGetImageUseCase(
        postRepository: PostRepository
    ): GetImageUseCase {
        return GetImageUseCase(postRepository)
    }

    @Singleton
    @Provides
    fun provideLikeUseCase(
        postRepository: PostRepository
    ): LikeUseCase {
        return LikeUseCase(postRepository)
    }

    @Singleton
    @Provides
    fun provideGetDetailPostUseCase(
            postRepository: PostRepository
    ): GetDetailPostUseCase {
        return GetDetailPostUseCase(postRepository)
    }
}