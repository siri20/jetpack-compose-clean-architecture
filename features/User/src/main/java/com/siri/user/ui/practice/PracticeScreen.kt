package com.siri.user.ui.practice

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun User(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    currentDestination: NavDestination?
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        UserNavHost(navController = navController, innerPadding = it)
    }

}