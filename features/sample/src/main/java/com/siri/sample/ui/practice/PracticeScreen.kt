package com.siri.sample.ui.practice

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Practice(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    currentDestination: NavDestination?
) {

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        PracticeNavHost(navController = navController, innerPadding = it)
    }

}