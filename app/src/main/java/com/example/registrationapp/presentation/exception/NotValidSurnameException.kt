package com.example.registrationapp.presentation.exception

class NotValidSurnameException : Exception() {
    override val message: String
        get() = "Invalid surname. The surname must consist of at least 2 letters"
}