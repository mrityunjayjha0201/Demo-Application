package com.freelancersworld

import android.app.Application
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@Stable
@HiltAndroidApp
class FreelancersWorldApp : MultiDexApplication(){
    val isDark = mutableStateOf(false)

    fun toggleTheme() {
        isDark.value = !isDark.value
    }
}