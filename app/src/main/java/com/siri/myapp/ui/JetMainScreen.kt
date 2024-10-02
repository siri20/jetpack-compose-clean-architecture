package com.siri.myapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.siri.core.ui.theme.JetShopeeTheme
import com.siri.myapp.utils.JetNavigationType
import com.siri.sample.ui.navigation.BottomNav
import com.siri.sample.ui.navigation.NavRail
import com.siri.sample.ui.navigation.model.BottomBarScreen
import com.siri.sample.ui.navigation.navdrawer.NavDrawer

@Composable
fun JetMainScreen(
    modifier: Modifier = Modifier,
    navigationType: JetNavigationType,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navigationItemContentList = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Cart,
        BottomBarScreen.Profile
    )

    when (navigationType) {
        JetNavigationType.BOTTOM_NAVIGATION -> {
            BottomNav(
                modifier = modifier,
                navigationItemContentList = navigationItemContentList,
                navController = navController,
                currentDestination = currentDestination,
            )
        }

        JetNavigationType.NAVIGATION_RAIL -> {
            NavRail(
                modifier = modifier,
                navigationItemContentList = navigationItemContentList,
                navController = navController,
                currentDestination = currentDestination,
            )
        }

        JetNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            NavDrawer(
                modifier = modifier,
                navigationItemContentList = navigationItemContentList,
                navController = navController,
                currentDestination = currentDestination,
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DefaultPreview() {
    JetShopeeTheme {
        JetMainScreen(navigationType = JetNavigationType.BOTTOM_NAVIGATION)
    }
}