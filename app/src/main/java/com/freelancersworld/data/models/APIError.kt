package com.freelancersworld.data.models

import androidx.compose.runtime.Stable

@Stable
data class APIError(val code: Long, val message: String)