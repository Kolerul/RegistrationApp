package com.example.registrationapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrationapp.domain.repository.UserDataRepository
import com.example.registrationapp.presentation.uistate.MainUIState
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(
    private val repository: UserDataRepository
): ViewModel() {

    private val _uiState = MutableLiveData<MainUIState>(MainUIState.Initializing)
    val uiState: LiveData<MainUIState>
        get() = _uiState

    fun getUser(){
        _uiState.value = MainUIState.Loading
        viewModelScope.launch {
            try {
                val user = repository.getUserData()
                _uiState.value = MainUIState.Success(user)
            }catch (e: Exception){
                _uiState.value = MainUIState.Error("Error: ${e.message}")
            }
        }
    }
}