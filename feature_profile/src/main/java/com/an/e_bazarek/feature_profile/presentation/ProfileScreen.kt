package com.an.e_bazarek.feature_profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.an.e_bazarek.shared_resources.domain.model.User


@Composable
fun ProfileScreen(
    bottomNav: @Composable () -> Unit,
    viewModel: ProfileViewModel
) {
    Scaffold(
        bottomBar = { bottomNav() }
    )  {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}