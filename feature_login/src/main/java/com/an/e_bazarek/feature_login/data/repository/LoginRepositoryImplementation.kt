package com.an.e_bazarek.feature_login.data.repository

import com.an.e_bazarek.feature_login.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepositoryImplementation @Inject constructor(
    private val auth: FirebaseAuth
) : LoginRepository {

    private val currentUser = auth.currentUser

    override suspend fun logIn(
        email: String,
        password: String
    ) {
        auth.signInWithEmailAndPassword(
            email,
            password
        )
    }

    override suspend fun logOut() {
        if(currentUser != null)
            auth.signOut()
    }

    override suspend fun signIn(
        email: String,
        password: String
    ) {
        auth.createUserWithEmailAndPassword(
            email,
            password
        )
    }

}