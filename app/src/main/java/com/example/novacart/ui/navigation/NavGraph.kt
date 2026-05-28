package com.example.novacart.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.novacart.ui.screens.detail.DetailScreen
import com.example.novacart.ui.screens.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {

            HomeScreen(

                onProductClick = {

                    navController.navigate(
                        Screen.Detail.route
                    )
                }
            )
        }

        composable(Screen.Detail.route) {

            DetailScreen()
        }
    }
}