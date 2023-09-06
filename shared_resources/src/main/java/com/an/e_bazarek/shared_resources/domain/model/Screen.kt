package com.an.e_bazarek.shared_resources.domain.model

sealed class Screen(val route: String) {
    object AuthFeature: Screen(route = "auth_feature")
    object BazarekFeature: Screen(route = "bazarek_feature")

    object Login: Screen(route = "login")
    object Register: Screen(route = "register")

    object Profile: Screen(route = "profile")
    object Market: Screen(route = "market")
    object Chat: Screen(route = "chat")
}
