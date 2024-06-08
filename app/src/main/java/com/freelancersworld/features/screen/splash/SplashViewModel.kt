package com.freelancersworld.features.screen.splash

import androidx.lifecycle.viewModelScope
import com.freelancersworld.data.datastore.StoreData
import com.freelancersworld.domain.viewstate.IViewEvent
import com.freelancersworld.domain.viewstate.splash.SplashViewState
import com.freelancersworld.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel<SplashViewState, SplashViewEvent>() {

    @Inject
    lateinit var storeData: StoreData

    init {
        viewModelScope.launch {
            delay(2000)
            storeData.setUserId(0L)
            storeData.getUserId().collect {
                if (it != 0L) {
                    setEvent(SplashViewEvent.DirectToMainActivity)
                } else {
                    setEvent(SplashViewEvent.DirectToLoginActivity)
                }
            }
        }
    }

    override fun createInitialState() = SplashViewState()
    override fun onTriggerEvent(event: SplashViewEvent) {}
}

sealed class SplashViewEvent : IViewEvent {
    data object DirectToMainActivity : SplashViewEvent()
    data object DirectToLoginActivity : SplashViewEvent()
}