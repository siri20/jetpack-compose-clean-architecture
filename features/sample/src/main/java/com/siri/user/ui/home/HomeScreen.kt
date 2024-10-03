package com.siri.user.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.siri.core.R
import com.siri.core.data.ProductUiState
import com.siri.core.data.model.ProductResponse
import com.siri.core.ui.component.molecules.SearchBar
import com.siri.core.ui.template.MainTemplate
import com.siri.core.ui.theme.Gray200
import com.siri.user.ui.component.ProgressProduct
import com.siri.user.ui.home.section.HomeContent



@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateToSearch: () -> Unit,
) {
    val uiStateProduct by remember { viewModel.productUiStateProduct }.collectAsState()

    MainTemplate(
        modifier = modifier,
        topBar = {
            SearchBar(
                query = "",
                onQueryChange = {},
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                isEnabled = false,
                onSearchClicked = { navigateToSearch() }
            )
        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .background(Gray200)
            ) {
                when (uiStateProduct) {
                    is ProductUiState.Loading -> {
                        viewModel.getProductsApiCall()
                        ProgressProduct()
                    }

                    is ProductUiState.Success -> {
                        HomeContent(
                            modifier = modifier,
                            listProduct = (uiStateProduct as ProductUiState.Success<ProductResponse>).data.products,
                            navigateToDetail = navigateToDetail,
                        )
                    }

                    is ProductUiState.Error -> {
                        Text(text = stringResource(R.string.error_product), color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        })
}