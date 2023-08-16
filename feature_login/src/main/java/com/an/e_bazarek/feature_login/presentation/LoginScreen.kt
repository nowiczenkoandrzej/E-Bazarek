package com.an.e_bazarek.feature_login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.an.e_bazarek.feature_login.domain.model.LoginScreenEvent


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel,
) {

    val state by viewModel.screenState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        TextField(
            value = state.email,
            onValueChange = { email ->
                viewModel.onEvent(LoginScreenEvent.TypeEmail(email))
            }
        )

        TextField(
            value = state.password,
            onValueChange = { password ->
                viewModel.onEvent(LoginScreenEvent.TypePassword(password))
            }
        )
    }


}

