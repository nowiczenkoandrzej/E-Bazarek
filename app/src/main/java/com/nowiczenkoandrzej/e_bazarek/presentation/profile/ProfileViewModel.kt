package com.nowiczenkoandrzej.e_bazarek.presentation.profile

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.nowiczenkoandrzej.e_bazarek.domain.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val repository: FirebaseRepository
): ViewModel(){
    private val _userState = repository.getCurrentUserData()
    val userState = _userState.asStateFlow()

    fun logout() = repository.logout()
}