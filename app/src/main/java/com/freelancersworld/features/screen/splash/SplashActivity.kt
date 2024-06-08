package com.freelancersworld.features.screen.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.freelancersworld.features.screen.main.MainActivity
import com.freelancersworld.features.screen.mobile.MobileNumberActivity
import com.freelancersworld.features.screen.otp.VerifyOtpActivity
import com.freelancersworld.utils.Utility.launchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        //init collect
        initCollect()
    }

    private fun initCollect() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiEvent.collect {
                    when(it) {
                        is SplashViewEvent.DirectToMainActivity -> {
                            startMainActivity()
                        }
                        is SplashViewEvent.DirectToLoginActivity -> {
                            startLoginActivity()
                        }
                    }
                }
            }
        }
    }

    private fun startMainActivity() {
        launchActivity(
            packageName = packageName,
            className = MainActivity::class.java
        )
        finish()
    }

    private fun startLoginActivity() {
        launchActivity(
            packageName = packageName,
            className = MobileNumberActivity::class.java
        )
        finish()
    }
}