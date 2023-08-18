package com.an.e_bazarek.feature_login.presentation

import androidx.lifecycle.ViewModel
import com.an.e_bazarek.feature_login.domain.model.LoginScreenEvent
import com.an.e_bazarek.feature_login.domain.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

): ViewModel() {

    private val _screenState = MutableStateFlow(LoginState())
    val screenState = _screenState.asStateFlow()

    fun onEvent(event: LoginScreenEvent) {
        when(event) {
            is LoginScreenEvent.TypeEmail -> {
                _screenState.value = screenState.value.copy(
                    email = event.email
                )
            }
            is LoginScreenEvent.TypePassword -> {
                _screenState.value = screenState.value.copy(
                    password = event.password
                )
            }
        }
    }


}