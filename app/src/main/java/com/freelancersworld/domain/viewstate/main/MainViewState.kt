package com.freelancersworld.domain.viewstate.main

import com.freelancersworld.data.models.PhotosResponse
import com.freelancersworld.domain.viewstate.IViewState

data class MainViewState (
    val photoList: MutableList<PhotosResponse> = mutableListOf(),
    val isLoading: Boolean = false,
) : IViewState