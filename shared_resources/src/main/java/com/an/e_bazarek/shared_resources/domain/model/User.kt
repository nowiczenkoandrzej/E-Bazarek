package com.an.e_bazarek.shared_resources.domain.model

data class User(
    val userID: String,
    val email: String,
    val username: String = "",
    val photoUrl: String? = null
)
