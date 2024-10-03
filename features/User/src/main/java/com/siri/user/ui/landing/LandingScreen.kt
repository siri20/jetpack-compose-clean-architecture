package com.siri.user.ui.landing

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
import com.siri.core.data.UserUiState
import com.siri.core.data.model.UsersResponse
import com.siri.core.ui.template.MainTemplate
import com.siri.core.ui.theme.Gray200
import com.siri.user.ui.component.ProgressUser
import com.siri.user.ui.landing.section.LandingContent

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
) {
    val uiStateUser by remember { viewModel.uiStateUser }.collectAsState()

    MainTemplate(
        modifier = modifier,
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .background(Gray200)
            ) {
                when (uiStateUser) {
                    is UserUiState.Loading -> {
                        viewModel.getProductsApiCall()
                        ProgressUser()
                    }

                    is UserUiState.Success -> {
                        LandingContent(
                            modifier = modifier,
                            listUsers = (uiStateUser as UserUiState.Success<UsersResponse>).data.users,
                            navigateToDetail = navigateToDetail,
                        )
                    }

                    is UserUiState.Error -> {
                        Text(text = stringResource(R.string.error_product), color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        })
}