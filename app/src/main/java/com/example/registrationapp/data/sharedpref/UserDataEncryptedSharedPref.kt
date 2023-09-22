package com.example.registrationapp.data.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.registrationapp.domain.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataEncryptedSharedPref @Inject constructor(
    private val context: Context
) {

    private val sharedPref: SharedPreferences

    init {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        val fileName = "User data"

        sharedPref = EncryptedSharedPreferences.create(
            context,
            fileName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun putUserData(user: User) {
        val editor = sharedPref.edit()
        user.apply {
            editor.putString(nameKey, name)
            editor.putString(surnameKey, surname)
            editor.putString(birthDateKey, birthDate)
            editor.putString(passwordKey, password)
        }
        editor.apply()
    }

    fun getUserData(): User =
        User(
            name = sharedPref.getString(nameKey, "") ?: "",
            surname = sharedPref.getString(surnameKey, "") ?: "",
            birthDate = sharedPref.getString(birthDateKey, "") ?: "",
            password = sharedPref.getString(passwordKey, "") ?: ""
        )


    private val nameKey = "Name"
    private val surnameKey = "Surname"
    private val birthDateKey = " Birth date"
    private val passwordKey = "Password"
}