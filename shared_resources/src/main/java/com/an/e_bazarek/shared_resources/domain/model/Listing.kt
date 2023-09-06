package com.an.e_bazarek.shared_resources.domain.model

data class Listing(
    val title: String,
    val description: String,
    val price: Int,
    val photoUrls: List<String>,
)
