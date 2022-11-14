package com.nowiczenkoandrzej.e_bazarek.presentation.products

import androidx.lifecycle.ViewModel
import com.nowiczenkoandrzej.e_bazarek.domain.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class ProductsViewModel
@Inject constructor(
    repository: FirebaseRepository
): ViewModel(){
    private val _products = repository.getAllProducts()
    val products = _products.asStateFlow()
}