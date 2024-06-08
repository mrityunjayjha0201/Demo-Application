package com.freelancersworld.features.screen.mobile

import androidx.lifecycle.viewModelScope
import com.freelancersworld.R
import com.freelancersworld.data.models.dto.MobileNumberDto
import com.freelancersworld.domain.viewstate.IViewEvent
import com.freelancersworld.domain.viewstate.mobilenumber.MobileNumberViewState
import com.freelancersworld.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MobileNumberViewModel @Inject constructor() :
    BaseViewModel<MobileNumberViewState, MobileNumberViewEvent>(){
    private val MAX_LENGTH_MOBILE:Int = 10

    override fun createInitialState() = MobileNumberViewState()

    override fun onTriggerEvent(event: MobileNumberViewEvent) {
            when(event) {
                is MobileNumberViewEvent.ValidateAndSendOtp -> {
                    validateAndSendOtp(currentState)
                }
                else -> {}
        }
    }

    private fun validateAndSendOtp(viewState: MobileNumberViewState) {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true, message = null, errMsg = null) }
            delay(100)
            if (viewState.mobileNumber.isNullOrEmpty() || viewState.mobileNumber.length < MAX_LENGTH_MOBILE) {
                setState { currentState.copy(isLoading = false, errMsg = R.string.validate_mobile_number) }
            } else {
                setEvent(MobileNumberViewEvent.GoToOtpScreen(MobileNumberDto(viewState.countryCode, viewState.mobileNumber)))
            }
        }
    }

    fun setCountryCodeText(value: String?) {
        viewModelScope.launch {
            value?.let {
                setState { currentState.copy(isLoading = false, countryCode = value) }
            }
        }
    }

    fun setMobileNumberText(value: String?) {
        viewModelScope.launch {
            value?.let {
                if (value.length <= MAX_LENGTH_MOBILE) {
                    setState { currentState.copy(isLoading = false, mobileNumber = value, message = null, errMsg = null) }
                }
            }
        }
    }
}

sealed class MobileNumberViewEvent : IViewEvent {
    data object ValidateAndSendOtp : MobileNumberViewEvent()
    class GoToOtpScreen(val mobileNumberDto: MobileNumberDto) : MobileNumberViewEvent()
}