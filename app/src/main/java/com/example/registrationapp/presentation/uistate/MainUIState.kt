package com.example.registrationapp.presentation.uistate

import com.example.registrationapp.domain.entity.User

sealed class MainUIState {
    object Initializing: MainUIState()
    object Loading: MainUIState()
    data class Success(val user: User): MainUIState()
    data class Error(val message: String): MainUIState()
}