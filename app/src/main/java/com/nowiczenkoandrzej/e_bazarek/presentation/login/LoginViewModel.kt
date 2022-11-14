package com.nowiczenkoandrzej.e_bazarek.presentation.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.nowiczenkoandrzej.e_bazarek.utils.AccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val fbAuth: FirebaseAuth
): ViewModel(){

    private val _loginState = MutableStateFlow<AccountState>(AccountState.Empty)
    val loginState = _loginState.asStateFlow()

    fun singIn(email: String, password: String) {

        _loginState.value = AccountState.Loading

        fbAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authRes ->
                if (authRes.user != null)
                    _loginState.value = AccountState.Success
                else
                    _loginState.value = AccountState.Failure("Ups... Something went wrong...")

            }
            .addOnFailureListener{
                _loginState.value = AccountState.Failure(it.message.toString())            }
    }

}