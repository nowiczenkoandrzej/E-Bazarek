package com.an.e_bazarek.feature_profile.presentation

import androidx.lifecycle.ViewModel
import com.an.e_bazarek.feature_profile.domain.model.ProfileScreenEvent
import com.an.e_bazarek.feature_profile.domain.model.ProfileScreenState
import com.an.e_bazarek.shared_resources.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(

): ViewModel() {

    private val _screenState = MutableStateFlow(ProfileScreenState())
    val screenState = _screenState.asStateFlow()

    init {
        _screenState.value = screenState.value.copy(
            user = User(
                userID = "123",
                username = "daniel",
                email = "test@gmail.com"
            )
        )
    }

    fun onEvent(event: ProfileScreenEvent) {
        when(event) {
            is ProfileScreenEvent.DisplayError -> TODO()
        }
    }

}