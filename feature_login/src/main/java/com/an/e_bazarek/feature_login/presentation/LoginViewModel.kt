package com.an.e_bazarek.feature_login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.an.e_bazarek.feature_login.domain.model.LoginEvent
import com.an.e_bazarek.feature_login.domain.model.LoginState
import com.an.e_bazarek.feature_login.domain.model.RegisterEvent
import com.an.e_bazarek.feature_login.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {

    private val _screenState = MutableStateFlow(LoginState())
    val screenState = _screenState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.TypeEmail -> {
                _screenState.value = screenState.value.copy(
                    email = event.email
                )
            }
            is LoginEvent.TypePassword -> {
                _screenState.value = screenState.value.copy(
                    password = event.password
                )
            }
            is LoginEvent.SingIn -> viewModelScope.launch {
                repository.signIn(
                    email = screenState.value.email.trim(),
                    password = screenState.value.password.trim()
                ).collect { loginState ->
                    _screenState.value = loginState
                }
            }
            is LoginEvent.SingInWithGoogle -> TODO()
            is LoginEvent.CreateAccount -> TODO()
            is LoginEvent.ForgotPassword -> TODO()
            is LoginEvent.DisplayError -> {
                _screenState.value = screenState.value.copy(
                    error = null
                )
            }

        }
    }




}