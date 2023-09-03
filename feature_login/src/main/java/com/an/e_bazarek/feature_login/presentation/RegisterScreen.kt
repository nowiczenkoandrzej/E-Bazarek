package com.an.e_bazarek.feature_login.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.an.e_bazarek.feature_login.R
import com.an.e_bazarek.feature_login.domain.model.LoginEvent
import com.an.e_bazarek.feature_login.domain.model.RegisterEvent

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel,
    signIn: () -> Unit
) {
    val state by viewModel.screenState.collectAsState()

    val context = LocalContext.current

    val focusRequesterPassword = remember { FocusRequester() }
    val focusRequesterRepeatPassword = remember { FocusRequester() }

    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    var repeatedPasswordVisibility by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = state.error) {
        state.error?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
            viewModel.onEvent(RegisterEvent.DisplayError)

        }
    }

    LaunchedEffect(key1 = state.isAccountCreated) { signIn() }

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

                visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {

                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        val image = if(passwordVisibility) {
                            painterResource(id = R.drawable.baseline_visibility_24)
                        } else {
                            painterResource(id = R.drawable.baseline_visibility_off_24)
                        }
                        Icon(
                            painter = image,
                            contentDescription = null
                        )
                    }
                },
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
                visualTransformation = if(repeatedPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {

                    IconButton(onClick = { repeatedPasswordVisibility = !repeatedPasswordVisibility }) {
                        val image = if(repeatedPasswordVisibility) {
                            painterResource(id = R.drawable.baseline_visibility_24)
                        } else {
                            painterResource(id = R.drawable.baseline_visibility_off_24)
                        }
                        Icon(
                            painter = image,
                            contentDescription = null
                        )
                    }
                },
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
                onClick = {
                    viewModel.onEvent(RegisterEvent.CreateAccount)
                    signIn()
                }
            ) {
                Text(text = "Create Account")
            }

        }
    }



}