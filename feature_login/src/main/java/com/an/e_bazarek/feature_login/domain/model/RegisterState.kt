package com.an.e_bazarek.feature_login.domain.model

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val repeatedPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
