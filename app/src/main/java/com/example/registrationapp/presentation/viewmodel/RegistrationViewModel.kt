package com.example.registrationapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrationapp.domain.entity.User
import com.example.registrationapp.domain.repository.UserDataRepository
import com.example.registrationapp.presentation.uistate.RegistrationUIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val repository: UserDataRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<RegistrationUIState>(RegistrationUIState.Initializing)
    val uiState: LiveData<RegistrationUIState>
        get() = _uiState

    fun registration(name: String, surname: String, birthDate: String, password: String) {
        _uiState.value = RegistrationUIState.Loading
        viewModelScope.launch {
            try {
                val user = User(name, surname, birthDate, password)
                val result = repository.saveUserData(user)
                if (result) _uiState.value = RegistrationUIState.Success
                else _uiState.value = RegistrationUIState.Error("Error: Something gone wrong")
            } catch (e: Exception) {
                _uiState.value = RegistrationUIState.Error("Error: ${e.message.toString()}")
            }
        }
    }

    fun checkIsRegistered() {
        _uiState.value = RegistrationUIState.Loading
        viewModelScope.launch {
            try {
                val user = repository.getUserData()
                if (user.name.isNotEmpty() &&
                    user.surname.isNotEmpty() &&
                    user.birthDate.isNotEmpty() &&
                    user.password.isNotEmpty()
                ) _uiState.value = RegistrationUIState.Success
                else _uiState.value = RegistrationUIState.Usual
            } catch (e: Exception) {
                _uiState.value = RegistrationUIState.Error("Error: ${e.message.toString()}")
            }
        }
    }
}