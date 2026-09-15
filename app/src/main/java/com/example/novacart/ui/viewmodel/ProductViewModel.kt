package com.example.novacart.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novacart.domain.model.Product
import com.example.novacart.domain.repository.ProductRepository
import com.example.novacart.ui.state.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        ProductUiState()
    )

    val state: StateFlow<ProductUiState> = _state

    init {
        getProducts()
    }

    fun getProducts() {

        viewModelScope.launch {

            _state.value = _state.value.copy(
                isLoading = true
            )

            try {

                val products = repository.getProducts()

                _state.value = _state.value.copy(
                    isLoading = false,
                    products = products
                )

            } catch (e: Exception) {

                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun searchProducts(query: String) {

        viewModelScope.launch {

            _state.value = _state.value.copy(
                isLoading = true,
                error = null,
                searchQuery = query
            )

            try {

                val products = repository.searchProducts(query)

                _state.value = _state.value.copy(
                    isLoading = false,
                    products = products
                )
            } catch (e: Exception) {

                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun clearSearch() {

        _state.value = _state.value.copy(
            searchQuery = ""
        )

        getProducts()
    }

    fun selectProduct(product: Product) {

        _state.value = _state.value.copy(
            selectedProduct = product
        )
    }

    fun getProductById(productId: Int) {

        viewModelScope.launch {

            _state.value = _state.value.copy(
                isLoading = true,
                error = null
            )

            try {

                val product = repository.getProductById(productId)

                _state.value = _state.value.copy(
                    isLoading = false,
                    selectedProduct = product
                )
            } catch (e: Exception) {

                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}