package com.an.e_bazarek.feature_login.presentation

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import com.an.e_bazarek.feature_login.domain.model.LoginEvent
import com.an.e_bazarek.feature_login.domain.model.RegisterEvent


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel,
) {

    val state by viewModel.screenState.collectAsState()

    val context = LocalContext.current

    val focusRequester = remember { FocusRequester() }

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
                text = "Login",
                fontSize = 48.sp,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(48.dp))
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(LoginEvent.TypeEmail(it)) },
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
                    onNext = { focusRequester.requestFocus() }
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(LoginEvent.TypePassword(it)) },
                placeholder = { Text(text = "Password") },
                leadingIcon = { Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null
                ) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Go
                ),
                keyboardActions = KeyboardActions(
                    onGo = { viewModel.onEvent(LoginEvent.SingIn) }
                ),
                modifier = Modifier.focusRequester(focusRequester)

            )
            TextButton(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 32.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Forgot password?",)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 32.dp,
                        end = 32.dp
                    ),
                onClick = { viewModel.onEvent(LoginEvent.SingIn) }
            ) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "or")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 32.dp,
                        end = 32.dp
                    ),
                onClick = { navController.navigate("register") }
            ) {
                Text(text = "Sign up")
            }

            if(state.error != null) {
                Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                viewModel.onEvent(LoginEvent.DisplayError)
            }

        }
    }

}
