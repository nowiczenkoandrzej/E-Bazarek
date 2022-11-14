package com.nowiczenkoandrzej.e_bazarek.data.models

data class UserResponse(
    val email: String = "",
    val favorites: List<String> = emptyList(),
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val products: List<String> = emptyList(),
    val score: Int = 0
)
