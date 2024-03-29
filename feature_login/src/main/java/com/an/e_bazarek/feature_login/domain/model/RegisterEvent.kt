package com.an.e_bazarek.feature_login.domain.model

sealed class RegisterEvent() {
    data class TypeEmail(val email: String): RegisterEvent()
    data class TypePassword(val password: String): RegisterEvent()
    data class TypeRepeatedPassword(val password: String): RegisterEvent()
    object CreateAccount: RegisterEvent()
    object DisplayError: RegisterEvent()
}
