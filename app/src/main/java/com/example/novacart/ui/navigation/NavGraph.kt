package com.example.novacart.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.example.novacart.ui.screens.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavController
) {

    NavHost(
        navController = navController,
        startDestinaion = Screen.Home.route
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

        composable(Screen.Detail.route){

            DetailScreen()
        }
    }
}