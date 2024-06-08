package com.freelancersworld.domain.viewstate.otp

import com.freelancersworld.domain.viewstate.IViewState

data class OtpViewState(
    val isLoading:Boolean = false,
    val mobileNumber:String ?= null,
    val otp1:String = "",
    val otp2:String = "",
    val otp3:String = "",
    val otp4:String = "",
    val errMsg:Int? = null
) : IViewState {
    fun otp() : String {
        return "$otp1$otp2$otp3$otp4"
    }
}