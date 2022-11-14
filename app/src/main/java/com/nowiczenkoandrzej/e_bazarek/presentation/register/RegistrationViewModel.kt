package com.nowiczenkoandrzej.e_bazarek.presentation.register

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.nowiczenkoandrzej.e_bazarek.utils.AccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel
@Inject constructor(
    private val fbAuth: FirebaseAuth
): ViewModel(){

    private val _registrationState = MutableStateFlow<AccountState>(AccountState.Empty)
    val registrationState = _registrationState.asStateFlow()

    fun createUser(email: String, password: String) {
        _registrationState.value = AccountState.Loading

        fbAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authRes ->
                if(authRes.user != null)
                    _registrationState.value = AccountState.Success
                else
                    _registrationState.value = AccountState.Failure("Ups... Something went wrong...")
            }
            .addOnFailureListener {
                _registrationState.value = AccountState.Failure(it.message.toString())
            }
    }
}