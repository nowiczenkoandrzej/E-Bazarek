package com.an.e_bazarek.feature_login.domain.model

sealed class LoginEvent() {
    data class TypeEmail(val email: String): LoginEvent()
    data class TypePassword(val password: String): LoginEvent()
    object SingIn: LoginEvent()
    object SingInWithGoogle: LoginEvent()
    object CreateAccount: LoginEvent()
    object ForgotPassword: LoginEvent()
    object DisplayError: LoginEvent()
}