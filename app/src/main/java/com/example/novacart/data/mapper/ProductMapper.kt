package com.example.novacart.data.mapper

import com.example.novacart.data.remote.dto.ProductDto
import com.example.novacart.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        price = price,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images
    )
}

fun List<ProductDto>.toDomain(): List<Product> {
    return map { it.toDomain() }
}