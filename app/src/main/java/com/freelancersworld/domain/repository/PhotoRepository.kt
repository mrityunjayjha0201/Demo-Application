package com.freelancersworld.domain.repository

import com.freelancersworld.data.models.PhotosResponse
import com.freelancersworld.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getAllPhotos(): Flow<DataState<MutableList<PhotosResponse>>>
    fun getPhoto(photoId: Int): Flow<DataState<PhotosResponse>>
}