package com.freelancersworld.data.repository

import com.freelancersworld.data.models.PhotosResponse
import com.freelancersworld.data.remote.source.PhotoRemoteDataSource
import com.freelancersworld.data.remote.utils.DataState
import com.freelancersworld.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val photoRemoteDataSource: PhotoRemoteDataSource) : PhotoRepository {
    override fun getAllPhotos(): Flow<DataState<MutableList<PhotosResponse>>> = flow {
        emitAll(photoRemoteDataSource.getAllPhotos())
    }

    override fun getPhoto(photoId: Int): Flow<DataState<PhotosResponse>> = flow {
        emitAll(photoRemoteDataSource.getPhoto(photoId))
    }
}
