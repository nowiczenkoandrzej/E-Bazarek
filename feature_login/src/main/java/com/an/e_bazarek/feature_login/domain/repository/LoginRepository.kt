package com.an.e_bazarek.feature_login.domain.repository

import android.content.Intent
import com.an.e_bazarek.feature_login.domain.model.LoginState
import com.an.e_bazarek.feature_login.domain.model.RegisterState
import com.an.e_bazarek.feature_login.domain.model.SignInResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun signIn(
        email: String,
        password: String
    ): Flow<LoginState>

    suspend fun signUp(
        email: String,
        password: String
    ): Flow<RegisterState>

    suspend fun singInWithGoogle(intent: Intent): SignInResult


}