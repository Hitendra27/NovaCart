package com.example.novacart.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object Detail : Screen("detail/{productId}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}