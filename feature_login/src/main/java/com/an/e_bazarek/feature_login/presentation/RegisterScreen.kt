package com.an.e_bazarek.feature_login.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.an.e_bazarek.feature_login.domain.model.LoginEvent
import com.an.e_bazarek.feature_login.domain.model.RegisterEvent

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel
) {
    val state by viewModel.screenState.collectAsState()

    val focusRequesterPassword = remember { FocusRequester() }
    val focusRequesterRepeatPassword = remember { FocusRequester() }

    if(state.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Create Account",
                fontSize = 48.sp,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(48.dp))
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(RegisterEvent.TypeEmail(it)) },
                placeholder = { Text(text = "E-mail") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequesterPassword.requestFocus() }
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(RegisterEvent.TypePassword(it)) },
                placeholder = { Text(text = "Password") },
                leadingIcon = { Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null
                ) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequesterRepeatPassword.requestFocus() }
                ),
                modifier = Modifier.focusRequester(focusRequesterPassword)

            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.repeatedPassword,
                onValueChange = { viewModel.onEvent(RegisterEvent.TypeRepeatedPassword(it)) },
                placeholder = { Text(text = "Repeat Password") },
                leadingIcon = { Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null
                ) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Go
                ),
                keyboardActions = KeyboardActions(
                    onGo = { viewModel.onEvent(RegisterEvent.CreateAccount) }
                ),
                 modifier = Modifier.focusRequester(focusRequesterRepeatPassword)

            )
            Spacer(modifier = Modifier.height(16.dp))


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 32.dp,
                        end = 32.dp
                    ),
                onClick = { viewModel.onEvent(RegisterEvent.CreateAccount) }
            ) {
                Text(text = "Create Account")
            }

        }
    }



}