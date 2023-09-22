package com.example.registrationapp.domain.entity


data class User(
    val name: String,
    val surname: String,
    val birthDate: String,
    val password: String
)