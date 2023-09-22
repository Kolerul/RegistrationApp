package com.example.registrationapp.presentation.exception

class PasswordNotConfirmedException : Exception() {
    override val message: String
        get() = "Passwords don't match"
}