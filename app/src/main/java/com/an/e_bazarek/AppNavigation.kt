package com.an.e_bazarek

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.an.e_bazarek.feature_profile.presentation.ProfileScreen
import com.an.e_bazarek.feature_profile.presentation.ProfileViewModel
import com.an.e_bazarek.shared_resources.domain.model.Screen

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Market.route
    ) {
        composable(Screen.Market.route) {
            Text(text = "market")
        }

        composable(Screen.Profile.route) {
            val viewModel = hiltViewModel<ProfileViewModel>()
            ProfileScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(Screen.Chat.route) {
            Text(text = "chat")
        }
    }

}