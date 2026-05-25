package com.example.novacart.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.novacart.domain.model.Product
import com.example.novacart.ui.viewmodel.ProductViewModel

@Composable
class HomeScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    onProductClick: (Product) -> Unit
) {

    val state by viewModel.state.collectAsState()

    if (state.isLoading) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    state.error?.let { error ->

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = error)
        }
    }

    else -> {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {

            items(state.products) { product ->

                ProductCard(
                    product = product,
                    onClick = {

                        viewModel.selectProduct(product)

                        onProductClick(product)
                    }
                )
            }
        }
    }
}