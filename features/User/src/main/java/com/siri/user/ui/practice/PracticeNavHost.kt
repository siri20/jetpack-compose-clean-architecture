package com.siri.user.ui.practice

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.siri.user.ui.details.DetailsScreen
import com.siri.user.ui.landing.UserScreen
import com.siri.user.ui.practice.model.DetailScreen
import com.siri.user.ui.practice.model.PracticeScreen

@Composable
fun UserNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = PracticeScreen.Landing.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(PracticeScreen.Landing.route) {
            UserScreen(
                navigateToDetail = { id ->
                    navController.navigate(DetailScreen.Detail.createRoute(id))
                }
            )
        }
        composable(
            route = DetailScreen.Detail.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType }),
        ) {
            val id = it.arguments?.getInt("userId") ?: -1
            DetailsScreen(
                id = id,
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }
    }
}