package com.an.e_bazarek.feature_login.domain.model

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)
