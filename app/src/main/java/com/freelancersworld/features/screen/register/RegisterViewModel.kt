package com.freelancersworld.features.screen.register

import androidx.lifecycle.viewModelScope
import com.freelancersworld.R
import com.freelancersworld.domain.viewstate.IViewEvent
import com.freelancersworld.domain.viewstate.register.RegisterViewState
import com.freelancersworld.features.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel  : BaseViewModel<RegisterViewState, RegisterViewEvent>(){

    override fun createInitialState(): RegisterViewState = RegisterViewState()

    override fun onTriggerEvent(event: RegisterViewEvent) {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true, errMsg = null) }
            when(event) {
                is RegisterViewEvent.ValidateAndSaveData -> {
                    validate(currentState)
                }
                else -> {}
            }
        }
    }

    fun firstNameText(value:String) {
        viewModelScope.launch {
            if (value.length < 50) {
                setState { currentState.copy(firstName = value) }
            }
        }
    }

    fun lastNameText(value:String) {
        viewModelScope.launch {
            if (value.length < 50) {
                setState { currentState.copy(lastName = value) }
            }
        }
    }

    fun emailText(value:String) {
        viewModelScope.launch {
            if (value.length < 50) {
                setState { currentState.copy(email = value) }
            }
        }
    }

    fun skillsText(value:String) {
        viewModelScope.launch {
            if (value.length < 300) {
                setState { currentState.copy(skills = value) }
            }
        }
    }

    fun bioText(value:String) {
        viewModelScope.launch {
            if (value.length < 3000) {
                setState { currentState.copy(bio = value) }
            }
        }
    }

    private suspend fun validate(state: RegisterViewState) {
        val msg = when {
            state.firstName.isNullOrEmpty() -> R.string.validate_first_name
            state.lastName.isNullOrEmpty() -> R.string.validate_last_name
            state.skills.isNullOrEmpty() -> R.string.validate_skill
            state.bio.isNullOrEmpty() -> R.string.validate_bio
            else -> null
        }
        delay(100)

        setState { currentState.copy(isLoading = false, errMsg = msg) }
    }
}

sealed class RegisterViewEvent : IViewEvent {
    data object ValidateAndSaveData : RegisterViewEvent()
    data object GoToNextActivity : RegisterViewEvent()
}