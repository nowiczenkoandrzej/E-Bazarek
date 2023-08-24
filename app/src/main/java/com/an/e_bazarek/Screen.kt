package com.an.e_bazarek

sealed class Screen(val route: String) {
    object Login: Screen(route = "login")
    object Register: Screen(route = "register")
}
