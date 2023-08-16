package com.an.e_bazarek.feature_login.domain.model

sealed class LoginScreenEvent() {
    data class TypeEmail(val email: String): LoginScreenEvent()
    data class TypePassword(val password: String): LoginScreenEvent()
}