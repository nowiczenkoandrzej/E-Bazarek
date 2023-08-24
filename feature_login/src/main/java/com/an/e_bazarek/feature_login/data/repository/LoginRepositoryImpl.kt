package com.an.e_bazarek.feature_login.data.repository

import com.an.e_bazarek.feature_login.domain.model.LoginState
import com.an.e_bazarek.feature_login.domain.model.RegisterState
import com.an.e_bazarek.feature_login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : LoginRepository {

    override suspend fun signIn(
        email: String,
        password: String
    ) = flow{
        emit(LoginState(isLoading = true))
        try {
            val result = auth.signInWithEmailAndPassword(
                email,
                password
            ).await()
            if(result.user != null) {
                emit(LoginState(isLoggedIn = true))
            } else {
                emit(LoginState(error = "Invalid e-mail or password"))
            }
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(LoginState(error = e.message))
        } catch (e: Exception) {
            emit(LoginState(error = e.message))
        }

    }

    override suspend fun signUp(
        email: String,
        password: String
    ) = flow {
        emit(RegisterState(isLoading = true))
        try {
            val result =auth.createUserWithEmailAndPassword(
                email,
                password
            ).await()
            if(result.user != null) {
                emit(RegisterState(isAccountCreated = true))
            } else {
                emit(RegisterState(error = "Something went wrong"))
            }
        } catch (e: Exception) {
            emit(RegisterState(error = e.message))
        }

        // TODO: sprawdzic czy hasla sa takie same

    }

}