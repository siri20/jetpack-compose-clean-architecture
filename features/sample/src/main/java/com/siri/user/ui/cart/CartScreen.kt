package com.siri.user.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.siri.core.R
import com.siri.core.data.ProductUiState
import com.siri.core.ui.theme.Gray200
import com.siri.user.ui.cart.section.CartContent
import com.siri.user.ui.component.ProgressProduct



@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.cart))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
            )
        }, content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray200)
                    .padding(it)
            ) {
                viewModel.productUiStateDbProducts.collectAsState(initial = ProductUiState.Loading).value.let { uiState ->
                    when (uiState) {
                        is ProductUiState.Loading -> {
                            viewModel.getProductsDb()
                            ProgressProduct()
                        }

                        is ProductUiState.Success -> {
                            CartContent(products = uiState.data, viewModel = viewModel, navigateToDetail = navigateToDetail)
                        }

                        is ProductUiState.Error -> {
                            Text(text = stringResource(R.string.error_product), color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }
            }
        })

}
