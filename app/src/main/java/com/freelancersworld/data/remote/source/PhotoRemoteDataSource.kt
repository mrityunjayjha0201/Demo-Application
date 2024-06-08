package com.freelancersworld.data.remote.source

import com.freelancersworld.data.models.PhotosResponse
import com.freelancersworld.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface PhotoRemoteDataSource {
    suspend fun getAllPhotos(): Flow<DataState<MutableList<PhotosResponse>>>
    suspend fun getPhoto(photoId: Int): Flow<DataState<PhotosResponse>>
}