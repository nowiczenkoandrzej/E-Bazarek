package com.an.e_bazarek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.an.e_bazarek.feature_login.data.google_auth_client.GoogleAuthUIClient
import com.an.e_bazarek.feature_login.presentation.LoginScreen
import com.an.e_bazarek.feature_login.presentation.LoginViewModel
import com.an.e_bazarek.feature_login.presentation.RegisterScreen
import com.an.e_bazarek.feature_login.presentation.RegisterViewModel
import com.an.e_bazarek.ui.theme.E_BazarekTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            E_BazarekTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Login.route
                ) {
                    composable(Screen.Login.route) {
                        val viewModel = hiltViewModel<LoginViewModel>()

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if(result.resultCode == RESULT_OK) {

                                }
                            }
                        )

                        LoginScreen(
                            navController = navController,
                            viewModel = viewModel,
                            launcher = launcher,
                            onSignInWithGoogle = {

                            }
                        )
                    }
                    composable(Screen.Register.route) {
                        val viewModel = hiltViewModel<RegisterViewModel>()
                        RegisterScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }

            }
        }
    }
}
