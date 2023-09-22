package com.example.registrationapp.domain.repository

import com.example.registrationapp.domain.entity.User

interface UserDataRepository {
    suspend fun saveUserData(user: User): Boolean

    suspend fun getUserData(): User
}