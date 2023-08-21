package com.an.e_bazarek.feature_login.presentation

import androidx.lifecycle.ViewModel
import com.an.e_bazarek.feature_login.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {



}