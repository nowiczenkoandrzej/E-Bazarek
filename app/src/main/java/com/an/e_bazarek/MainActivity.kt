package com.an.e_bazarek


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.an.e_bazarek.feature_login.presentation.LoginScreen
import com.an.e_bazarek.feature_login.presentation.LoginViewModel
import com.an.e_bazarek.feature_login.presentation.RegisterScreen
import com.an.e_bazarek.feature_login.presentation.RegisterViewModel
import com.an.e_bazarek.feature_profile.presentation.ProfileScreen
import com.an.e_bazarek.feature_profile.presentation.ProfileViewModel
import com.an.e_bazarek.shared_resources.domain.model.BottomNavigationItem
import com.an.e_bazarek.shared_resources.domain.model.Screen
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

                val bottomNavItems = listOf(
                    BottomNavigationItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false,
                        route = Screen.Profile.route
                    ),
                    BottomNavigationItem(
                        title = "Market Place",
                        selectedIcon = Icons.Filled.List,
                        unselectedIcon = Icons.Outlined.List,
                        hasNews = false,
                        route = Screen.Market.route
                    ),
                    BottomNavigationItem(
                        title = "Messages",
                        selectedIcon = Icons.Filled.Email,
                        unselectedIcon = Icons.Outlined.Email,
                        hasNews = false,
                        route = Screen.Chat.route
                    )
                )


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
                                signIn =  {
                                    navController.navigate(Screen.BazarekFeature.route) {
                                        popUpTo(Screen.AuthFeature.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        composable(Screen.Register.route) {
                            val viewModel = hiltViewModel<RegisterViewModel>()
                            RegisterScreen(
                                navController = navController,
                                viewModel = viewModel,
                                signIn =  {
                                    navController.navigate(Screen.BazarekFeature.route) {
                                        popUpTo(Screen.AuthFeature.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                    }
                    navigation(
                        startDestination = Screen.App.route,
                        route = Screen.BazarekFeature.route
                    ) {
                        composable(Screen.App.route) {
                            var selectedItem by rememberSaveable {
                                mutableStateOf(1)
                            }
                            val appNavController = rememberNavController()
                            Scaffold(
                                bottomBar = {
                                    BottomNavigation() {
                                        BottomNavigation() {
                                            bottomNavItems.forEachIndexed { index, item ->
                                                BottomNavigationItem(
                                                    selected = selectedItem == index,
                                                    onClick = {
                                                        appNavController.navigate(item.route)
                                                        selectedItem = index
                                                              },
                                                    label = { Text(text = item.title) },
                                                    icon = {
                                                        if(selectedItem == index)
                                                            Icon(
                                                                imageVector = item.selectedIcon,
                                                                contentDescription = null
                                                            )
                                                        else
                                                            Icon(
                                                                imageVector = item.unselectedIcon,
                                                                contentDescription = null
                                                            )

                                                    }
                                                )
                                            }
                                        }
                                    }
                                }
                            ) { paddingValues ->
                                AppNavigation(
                                    navController = appNavController,
                                    modifier = Modifier.padding(paddingValues)
                                )

                            }
                        }
                    }

                }


            }
        }
    }
}
