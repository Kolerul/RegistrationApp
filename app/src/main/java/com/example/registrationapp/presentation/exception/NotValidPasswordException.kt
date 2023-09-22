package com.example.registrationapp.presentation.exception

class NotValidPasswordException : Exception() {
    override val message: String
        get() = "Invalid password. The password must contain at least one uppercase and lowercase letter," +
                " as well as a number. The minimum length is 5 characters."
}