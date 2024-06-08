package com.freelancersworld.features.screen.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.freelancersworld.data.models.PhotosResponse
import com.freelancersworld.data.remote.api.PhotoService
import com.freelancersworld.data.remote.utils.DataState
import com.freelancersworld.domain.repository.PhotoRepository
import com.freelancersworld.domain.viewstate.IViewEvent
import com.freelancersworld.domain.viewstate.main.MainViewState
import com.freelancersworld.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val photoRepository: PhotoRepository) : BaseViewModel<MainViewState, MainViewEvent>() {
    override fun createInitialState(): MainViewState = MainViewState()
    override fun onTriggerEvent(event: MainViewEvent) {
        when (event) {
            is MainViewEvent.PhotoApiRequest -> {
                photoApiRequest()
            }

            else -> {}
        }
    }

    private fun photoApiRequest() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true, photoList = mutableListOf()) }
            delay(2000)
            photoRepository.getAllPhotos().collect {
                when (it) {
                    is DataState.Success -> {
                        setState { currentState.copy(photoList = it.data, isLoading = false) }
                    }
                    is DataState.Error -> {
                        setState { currentState.copy(isLoading = false) }
                        setEvent(MainViewEvent.ShowError(it.apiError?.message))

                    }
                    is DataState.Loading -> {
                        setState { currentState.copy(isLoading = true) }
                    }
                }
            }
        }
    }
}

sealed class MainViewEvent: IViewEvent {
    data object PhotoApiRequest : MainViewEvent()
    class ShowError(val msg: String?) : MainViewEvent()
}