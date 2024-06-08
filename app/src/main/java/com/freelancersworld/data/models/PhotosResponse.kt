package com.freelancersworld.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotosResponse(
    val albumId: Long,
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
) : Parcelable