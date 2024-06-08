package com.freelancersworld.domain.viewstate.mobilenumber

import androidx.compose.runtime.Stable
import com.freelancersworld.domain.viewstate.IViewState

@Stable
data class MobileNumberViewState(
    val mobileNumber: String? = null,
    val countryCode:String? = null,
    val isLoading: Boolean = false,
    val message:String? = null,
    val errMsg:Int? = null,
) : IViewState