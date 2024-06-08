package com.freelancersworld.data.remote.source.impl

import com.freelancersworld.data.models.PhotosResponse
import com.freelancersworld.data.remote.api.PhotoService
import com.freelancersworld.data.remote.source.PhotoRemoteDataSource
import com.freelancersworld.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRemoteDataSourceImpl @Inject constructor(private val photoService: PhotoService) : BaseRemoteDataSource(), PhotoRemoteDataSource{

    override suspend fun getAllPhotos(): Flow<DataState<MutableList<PhotosResponse>>> = getResult {
        photoService.getAllPhotos()
    }

    override suspend fun getPhoto(photoId: Int): Flow<DataState<PhotosResponse>>  = getResult {
        photoService.getPhotos(photoId)
    }
}
