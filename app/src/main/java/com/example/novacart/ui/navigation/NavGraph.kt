package com.example.novacart.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.novacart.ui.screens.detail.DetailScreen
import com.example.novacart.ui.screens.home.HomeScreen
import com.example.novacart.ui.viewmodel.ProductViewModel

@Composable
fun NavGraph(
    navController: NavHostController
) {

    val viewModel: ProductViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {

            HomeScreen(
                viewModel = viewModel,

                onProductClick = { product ->

                    navController.navigate(
                        Screen.Detail.createRoute(product.id)
                    )
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf()
        ) {

            DetailScreen()
        }
    }
}