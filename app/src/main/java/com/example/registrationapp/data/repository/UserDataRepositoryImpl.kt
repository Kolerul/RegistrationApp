package com.example.registrationapp.data.repository

import com.example.registrationapp.data.sharedpref.UserDataEncryptedSharedPref
import com.example.registrationapp.domain.entity.User
import com.example.registrationapp.domain.repository.UserDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepositoryImpl @Inject constructor(
    private val sharedPreferences: UserDataEncryptedSharedPref,
    private val dispatcher: CoroutineDispatcher
) : UserDataRepository {

    override suspend fun saveUserData(user: User): Boolean = withContext(dispatcher) {
        sharedPreferences.putUserData(user)
        true
    }

    override suspend fun getUserData(): User = withContext(dispatcher) {
        sharedPreferences.getUserData()
    }

}