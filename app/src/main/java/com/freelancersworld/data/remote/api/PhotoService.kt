package com.freelancersworld.data.remote.api

import com.freelancersworld.data.models.PhotosResponse
import com.freelancersworld.data.remote.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoService {
    @GET(Constants.PHOTOS)
    suspend fun getAllPhotos(): Response<MutableList<PhotosResponse>>

    @GET(Constants.PHOTOS)
    suspend fun getPhotos(photoId:Int): Response<PhotosResponse>
}