package com.example.novacart.domain.repository

import com.example.novacart.data.mapper.toDomain
import com.example.novacart.data.remote.api.ProductApi
import com.example.novacart.domain.model.Product

class ProductRepositoryImpl(
    private val api: ProductApi
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return api.getProducts().products.toDomain()
    }

    override suspend fun getProductById(id: Int): Product {
        return api.getProductById(id).toDomain()
    }

    override suspend fun searchProducts(query: String): List<Product> {
        return api.searchProducts(query).products.toDomain()
    }

    override suspend fun getProductsByCategory(category: String): List<Product> {
        return api.getProductsByCategory(category).products.toDomain()
    }
}