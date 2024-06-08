package com.freelancersworld.data.models.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MobileNumberDto(
    val countryCode:String? = "91",
    val mobileNumber:String?
) : Parcelable {
    companion object {
        fun  init() = MobileNumberDto(
            countryCode = null,
            mobileNumber = null
        )
    }

    fun getMobileNumberWithCountryCode(): String {
        return "$countryCode $mobileNumber"
    }
}