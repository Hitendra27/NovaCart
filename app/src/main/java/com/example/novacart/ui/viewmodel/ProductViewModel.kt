package com.example.novacart.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novacart.domain.repository.ProductRepository
import com.example.novacart.ui.state.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        ProductUiState()
    )

    val state: StateFlow<ProductUiState> = _state

    private var searchJob: Job? = null

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

        searchJob?.cancel()

        searchJob = viewModelScope.launch {

            _state.value = _state.value.copy(
                searchQuery = query,
                error = null
            )

            delay(500)

            if (query.isBlank()) {
            getProducts()
                return@launch
            }

            _state.value = _state.value.copy(
                isLoading = true
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