package com.freelancersworld.features.screen.mobile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.freelancersworld.data.models.dto.MobileNumberDto
import com.freelancersworld.features.base.BaseActivity
import com.freelancersworld.features.screen.otp.VerifyOtpActivity
import com.freelancersworld.utils.Constants
import com.freelancersworld.utils.Utility.launchActivity

class MobileNumberActivity : BaseActivity(){

    private val viewModel:MobileNumberViewModel by viewModels<MobileNumberViewModel>()

    @Composable
    override fun GetSurface() {
        MobileNumberView(viewModel = viewModel)
    }

    override fun viewCreated() {
        scope {
            viewModel.setCountryCodeText("+91")
            viewModel.uiEvent.collect {
                when(it) {
                    is MobileNumberViewEvent.GoToOtpScreen -> goToOTPScreen(it.mobileNumberDto)
                    else -> {}
                }
            }
        }
    }

    private fun goToOTPScreen(mobileNumberDto: MobileNumberDto) {
        launchActivity(
            packageName = packageName,
            className = VerifyOtpActivity::class.java,
            bundle = Bundle().apply {putParcelable(Constants.IntentConstants.MOBILE_NUMBER_DTO, mobileNumberDto)}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileNumberView(viewModel = hiltViewModel())
}


