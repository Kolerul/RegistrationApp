package com.example.registrationapp.presentation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataValidator @Inject constructor() {

    fun checkName(name: String): Boolean {
        return name.length >= 2
    }

    fun checkSurname(surname: String): Boolean {
        return surname.length >= 2
    }

    fun checkBirthDate(birthDate: String): Boolean {
        return birthDate.isNotEmpty()
    }

    fun checkPassword(password: String): Boolean {
        val A = 65
        val Z = 90
        val zero = 48
        val nine = 57
        val a = 97
        val z = 122

        if (password.length < 5 || password.chars().filter { x -> x in A..Z }.count() == 0L ||
            password.chars().filter { x -> x in zero..nine }.count() == 0L ||
            password.chars().filter { x -> x in a..z }.count() == 0L
        ) return false

        return true
    }

    fun checkPasswordConfirmation(password: String, confirm: String): Boolean {
        return password == confirm
    }
}