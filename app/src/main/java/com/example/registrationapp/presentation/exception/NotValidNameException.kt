package com.example.registrationapp.presentation.exception

class NotValidNameException : Exception() {
    override val message: String
        get() = "Invalid name. The name must consist of at least 2 letters"
}