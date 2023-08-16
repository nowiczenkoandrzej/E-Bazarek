package com.an.e_bazarek.feature_login.domain.repository

interface LoginRepository {

    suspend fun logIn(
        email: String,
        password: String
    )

    suspend fun logOut()

    suspend fun signIn(
        email: String,
        password: String
    )


}