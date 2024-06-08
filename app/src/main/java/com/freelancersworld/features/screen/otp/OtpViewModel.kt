package com.freelancersworld.features.screen.otp

import androidx.lifecycle.viewModelScope
import com.freelancersworld.R
import com.freelancersworld.data.models.dto.MobileNumberDto
import com.freelancersworld.domain.viewstate.IViewEvent
import com.freelancersworld.domain.viewstate.otp.OtpViewState
import com.freelancersworld.features.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OtpViewModel : BaseViewModel<OtpViewState, OtpViewEvent>() {

    override fun createInitialState(): OtpViewState = OtpViewState()

    override fun onTriggerEvent(event: OtpViewEvent) {
        viewModelScope.launch {
            when(event) {
                is OtpViewEvent.ValidateOtp -> {
                    setState { currentState.copy(isLoading = true, errMsg = null) }

                    delay(100)
                    when (currentState.otp()) {
                        "1234" -> {
                            setEvent(OtpViewEvent.GoToHomeScreen)
                        }
                        "9876" -> {
                            setEvent(OtpViewEvent.GoToRegisterScreen)
                        }
                        else -> {
                            setState { currentState.copy(isLoading = false, errMsg = R.string.validate_otp) }
                        }
                    }
                }
                else -> {}
            }
        }
    }

    fun otpText(key:String, value:String) {
        viewModelScope.launch {
            if (value.length <= 1) {
                when(key) {
                    "1" -> otp1(value)
                    "2" -> otp2(value)
                    "3" -> otp3(value)
                    "4" -> otp4(value)
                }
            }
        }
    }

    fun setMobileNumberDto(mobileNumberDto: MobileNumberDto) {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = false, mobileNumber = mobileNumberDto.getMobileNumberWithCountryCode()) }
        }
    }

    private fun otp1(value: String) {
        setState { currentState.copy(otp1 = value) }
    }

    private fun otp2(value: String) {
        setState { currentState.copy(otp2 = value) }
    }

    private fun otp3(value: String) {
        setState { currentState.copy(otp3 = value) }
    }

    private fun otp4(value: String) {
        setState { currentState.copy(otp4 = value) }
    }
}

sealed class OtpViewEvent : IViewEvent {
    data object ValidateOtp : OtpViewEvent()
    data object GoToHomeScreen : OtpViewEvent()
    data object GoToRegisterScreen : OtpViewEvent()
}