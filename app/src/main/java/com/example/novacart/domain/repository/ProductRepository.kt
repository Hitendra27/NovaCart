package com.example.novacart.domain.repository

import com.example.novacart.domain.model.Product

interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun getProductById(id: Int): Product

    suspend fun searchProducts(query: String): List<Product>

    suspend fun getProductsByCategory(category: String): List<Product>
}