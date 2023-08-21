package com.an.e_bazarek.feature_login.data.repository

import android.util.Log
import com.an.e_bazarek.feature_login.domain.model.LoginState
import com.an.e_bazarek.feature_login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : LoginRepository {

    private val currentUser = auth.currentUser

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

    override suspend fun logOut() {
        if(currentUser != null)
            auth.signOut()
    }

    override suspend fun signUp(
        email: String,
        password: String
    ) {
        auth.createUserWithEmailAndPassword(
            email,
            password
        )
    }

}