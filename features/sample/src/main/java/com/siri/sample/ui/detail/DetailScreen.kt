package com.siri.sample.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.siri.core.data.UiState
import com.siri.core.ui.theme.Gray200
import com.siri.sample.ui.component.ProgressProduct
import com.siri.sample.ui.detail.section.DetailContent

/** Created by github.com/im-o on 12/22/2022. */

@Composable
fun DetailScreen(
    productId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.detail_product))
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Icon",
                            tint = Color.White,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                )
            )
        }, content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray200)
                    .padding(it)
            ) {
                viewModel.uiStateProduct.collectAsState(initial = UiState.Loading).value.let { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            viewModel.getProductByIdApiCall(productId)
                            ProgressProduct()
                        }

                        is UiState.Success -> {
                            DetailContent(product = uiState.data, viewModel = viewModel)
                        }

                        is UiState.Error -> {
                            Text(text = stringResource(R.string.error_product), color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }
            }
        })
}