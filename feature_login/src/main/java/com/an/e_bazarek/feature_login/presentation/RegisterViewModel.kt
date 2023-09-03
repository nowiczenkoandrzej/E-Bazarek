package com.an.e_bazarek.feature_login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.an.e_bazarek.feature_login.domain.model.RegisterEvent
import com.an.e_bazarek.feature_login.domain.model.RegisterState
import com.an.e_bazarek.feature_login.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {


    private val _screenState = MutableStateFlow(RegisterState())
    val screenState = _screenState.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.TypeEmail -> {
                _screenState.value = screenState.value.copy(
                    email = event.email
                )
            }
            is RegisterEvent.TypePassword -> {
                _screenState.value = screenState.value.copy(
                    password = event.password
                )
            }
            is RegisterEvent.TypeRepeatedPassword -> {
                _screenState.value = screenState.value.copy(
                    repeatedPassword = event.password
                )
            }
            is RegisterEvent.CreateAccount -> viewModelScope.launch {
                repository.signUp(
                    email = screenState.value.email.trim(),
                    password = screenState.value.password.trim()
                ).collect { registerState ->
                    _screenState.value = registerState
                }
            }
            is RegisterEvent.DisplayError -> {
                _screenState.value = screenState.value.copy(
                    error = null
                )
            }
        }
    }

}