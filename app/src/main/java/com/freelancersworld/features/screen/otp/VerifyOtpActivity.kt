package com.freelancersworld.features.screen.otp

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.freelancersworld.features.base.BaseActivity
import com.freelancersworld.features.screen.main.MainActivity
import com.freelancersworld.features.screen.register.RegisterActivity
import com.freelancersworld.utils.Constants
import com.freelancersworld.utils.Utility.launchActivity
import com.freelancersworld.utils.parcelable


class VerifyOtpActivity : BaseActivity() {

    private val viewModel by viewModels<OtpViewModel> ()

    @Composable
    override fun GetSurface() {
        VerifyOtpView(viewModel)
    }

    override fun viewCreated() {
        scope {
            viewModel.setMobileNumberDto(intent.parcelable(Constants.IntentConstants.MOBILE_NUMBER_DTO)!!)

            viewModel.uiEvent.collect {
                when(it) {
                    is OtpViewEvent.GoToRegisterScreen -> goToRegisterScreen()
                    is OtpViewEvent.GoToHomeScreen -> goToHomeScreen()
                    else -> {}
                }
            }
        }
    }

    private fun goToRegisterScreen() {
        launchActivity(
            className = RegisterActivity::class.java
        )
    }

    private fun goToHomeScreen() {
        launchActivity(
            className = MainActivity::class.java
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    VerifyOtpView(viewModel = hiltViewModel())
}