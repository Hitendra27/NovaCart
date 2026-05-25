package com.example.novacart.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.novacart.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
}