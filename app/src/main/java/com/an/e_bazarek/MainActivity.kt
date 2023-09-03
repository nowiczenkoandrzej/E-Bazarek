package com.an.e_bazarek


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.an.e_bazarek.feature_login.presentation.LoginScreen
import com.an.e_bazarek.feature_login.presentation.LoginViewModel
import com.an.e_bazarek.feature_login.presentation.RegisterScreen
import com.an.e_bazarek.feature_login.presentation.RegisterViewModel
import com.an.e_bazarek.model.Screen
import com.an.e_bazarek.ui.theme.E_BazarekTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            E_BazarekTheme {
                val navController = rememberNavController()

                val currentUser = FirebaseAuth.getInstance().currentUser
                val startDestination = remember {
                    if(currentUser == null) {
                        Screen.AuthFeature.route
                    } else {
                        Screen.BazarekFeature.route
                    }
                }

                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }



                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {

                    navigation(
                        startDestination = Screen.Login.route,
                        route = Screen.AuthFeature.route
                    ) {
                        composable(Screen.Login.route) {
                            val viewModel = hiltViewModel<LoginViewModel>()

                            LoginScreen(
                                navController = navController,
                                viewModel = viewModel,
                                signIn =  { navController.navigate(Screen.BazarekFeature.route) }
                            )
                        }
                        composable(Screen.Register.route) {
                            val viewModel = hiltViewModel<RegisterViewModel>()
                            RegisterScreen(
                                navController = navController,
                                viewModel = viewModel,
                                signIn =  { navController.navigate(Screen.BazarekFeature.route) }
                            )
                        }
                    }
                    navigation(
                        startDestination = Screen.Profile.route,
                        route = Screen.BazarekFeature.route
                    ) {
                        composable(Screen.Profile.route) {

                        }
                    }

                }


            }
        }
    }
}
