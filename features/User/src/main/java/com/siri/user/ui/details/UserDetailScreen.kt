package com.siri.user.ui.details

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
import com.siri.core.data.UserUiState
import com.siri.core.ui.theme.Gray200
import com.siri.user.ui.component.ProgressProduct
import com.siri.user.ui.details.section.UserDetailContent

@Composable
fun DetailsScreen(
    id: Int,
    viewModel: UserDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "User Details")
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
                viewModel.uiStateUser.collectAsState(initial = UserUiState.Loading).value.let { uiState ->
                    when (uiState) {
                        is UserUiState.Loading -> {
                            viewModel.getUserByIdApiCall(id = id)
                            ProgressProduct()
                        }

                        is UserUiState.Success -> {
                            UserDetailContent(user = uiState.data)
                        }

                        is UserUiState.Error -> {
                            Text(text = stringResource(R.string.error_product), color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }
            }
        })
}