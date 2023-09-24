package com.example.registrationapp.presentation.uistate

sealed class RegistrationUIState {
    object Initializing : RegistrationUIState()
    object Loading : RegistrationUIState()
    object Usual : RegistrationUIState()
    object Success : RegistrationUIState()
    data class Error(val message: String) : RegistrationUIState()
}