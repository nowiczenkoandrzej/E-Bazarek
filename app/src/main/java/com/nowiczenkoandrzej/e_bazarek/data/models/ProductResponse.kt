package com.nowiczenkoandrzej.e_bazarek.data.models

data class ProductResponse(
    val description: String = "",
    val id: String = "",
    val images: List<String> = emptyList(),
    val owner: String = "",
    val price: Double = 0.0,
    val title: String = ""
)