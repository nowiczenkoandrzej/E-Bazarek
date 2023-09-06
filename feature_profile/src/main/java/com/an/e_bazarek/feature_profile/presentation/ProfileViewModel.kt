package com.an.e_bazarek.feature_profile.presentation

import androidx.lifecycle.ViewModel
import com.an.e_bazarek.feature_profile.domain.model.ProfileScreenEvent
import com.an.e_bazarek.feature_profile.domain.model.ProfileScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(

): ViewModel() {

    private val _screenState = MutableStateFlow(ProfileScreenState())
    val screenState = _screenState.asStateFlow()

    fun onEvent(event: ProfileScreenEvent) {
        when(event) {
            is ProfileScreenEvent.DisplayError -> TODO()
        }
    }

}