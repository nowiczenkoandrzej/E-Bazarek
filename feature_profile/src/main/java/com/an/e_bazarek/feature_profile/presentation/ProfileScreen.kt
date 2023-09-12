package com.an.e_bazarek.feature_profile.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.an.e_bazarek.BottomNavBar
import com.an.e_bazarek.shared_resources.domain.model.User


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    navController: NavController
) {
    val state by viewModel.screenState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if(state.user != null) {
            UserData(
                user = state.user!!,
                amountOfListings = state.userListings.size
            )
        }
    }
}
