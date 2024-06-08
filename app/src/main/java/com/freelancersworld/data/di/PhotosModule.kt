package com.freelancersworld.data.di

import androidx.compose.runtime.Stable
import com.freelancersworld.data.remote.api.PhotoService
import com.freelancersworld.data.remote.source.PhotoRemoteDataSource
import com.freelancersworld.data.remote.source.impl.PhotoRemoteDataSourceImpl
import com.freelancersworld.data.repository.PhotoRepositoryImpl
import com.freelancersworld.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Stable
@Module
@InstallIn(ViewModelComponent::class)
class PhotosModule {
    @Provides
    fun provideTestService(retrofit: Retrofit): PhotoService =
        retrofit.create(PhotoService::class.java)

    @Provides
    fun providePhotoRemoteDataSource(photoService: PhotoService): PhotoRemoteDataSource =
        PhotoRemoteDataSourceImpl(photoService)

    @Provides
    fun providePhotoRepository(
        episodesRemoteDataSource: PhotoRemoteDataSource
    ): PhotoRepository =
        PhotoRepositoryImpl(episodesRemoteDataSource)
}
