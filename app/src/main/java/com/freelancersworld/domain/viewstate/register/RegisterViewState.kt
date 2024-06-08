package com.freelancersworld.domain.viewstate.register

import com.freelancersworld.domain.viewstate.IViewState

data class RegisterViewState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val errMsg: Int? = null,
    val firstName:String? = null,
    val lastName:String? = null,
    val email:String? = null,
    val skills:String? = null,
    val bio:String? = null,
) : IViewState