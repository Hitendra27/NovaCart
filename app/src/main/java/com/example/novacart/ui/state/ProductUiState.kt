package com.example.novacart.ui.state

import com.example.novacart.domain.model.Product

data class ProductUiState(

    val isLoading: Boolean = false,

    val products: List<Product> = emptyList(),

    val selectedProduct: Product? = null,

    val searchQuery: String = "",

    val error: String? = null
)
