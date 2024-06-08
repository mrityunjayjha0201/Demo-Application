package com.freelancersworld.domain.viewstate.splash

import androidx.compose.runtime.Stable
import com.freelancersworld.domain.viewstate.IViewState

@Stable
data class SplashViewState(
    val isLoading: Boolean = false
) : IViewState