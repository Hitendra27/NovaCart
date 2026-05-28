package com.example.novacart.data.mapper

import com.example.novacart.data.remote.dto.ProductDto
import com.example.novacart.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id ?: 0,
        title = title ?: "",
        description = description ?: "",
        price = price ?: 0.0,
        rating = rating ?: 0.0,
        stock = stock ?: 0,
        brand = brand ?: "",
        category = category ?: "",
        thumbnail = thumbnail ?: "",
        images = images ?: emptyList()
    )
}

fun List<ProductDto>.toDomain(): List<Product> {
    return map { it.toDomain() }
}