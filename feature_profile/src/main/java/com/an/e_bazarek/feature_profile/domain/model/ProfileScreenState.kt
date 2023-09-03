package com.an.e_bazarek.feature_profile.domain.model

import com.an.e_bazarek.shared_resources.domain.model.User

data class ProfileScreenState(
    val user: User,
    val userProducts: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)