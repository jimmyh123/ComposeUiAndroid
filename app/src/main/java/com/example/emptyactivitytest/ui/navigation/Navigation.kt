package com.example.emptyactivitytest.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.emptyactivitytest.R
import com.example.emptyactivitytest.ScreenThree
import com.example.emptyactivitytest.ScreenTwo
import com.example.emptyactivitytest.ui.screens.ContentColumnComposable
import com.example.emptyactivitytest.ui.screens.navbar.NotificationsScreen
import com.example.emptyactivitytest.ui.screens.navbar.SearchScreen
import com.example.emptyactivitytest.ui.screens.navdrawer.FavouritesScreen
import com.example.emptyactivitytest.ui.screens.navdrawer.HomeScreen
import com.example.emptyactivitytest.ui.screens.navdrawer.InboxScreen
import com.example.emptyactivitytest.ui.screens.navdrawer.NewMessageScreen
import com.example.emptyactivitytest.ui.screens.navdrawer.SettingsScreen
import com.example.emptyactivitytest.ui.screens.navdrawer.TrashScreen

sealed class AppScreen(
    @StringRes val title: Int,
    val route: String
) {
    object MainScreen : AppScreen(
        R.string.main_screen,
        NavRoutes.MAIN_SCREEN
    )
    object ScreenTwo : AppScreen(
        R.string.screen_two,
        NavRoutes.SCREEN_TWO
    )
    object ScreenThree : AppScreen(
        R.string.screen_three,
        NavRoutes.SCREEN_THREE
    )
}

class Actions(navController: NavHostController) {

    val onNextButtonScreenOne: () -> Unit = {
        navController.navigate(AppScreen.ScreenTwo.route)
    }
}

@Composable
fun MyNavHost(navController: NavHostController, innerPadding: PaddingValues) {

    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = AppScreen.MainScreen.route) {

        // main destinations
        composable(AppScreen.MainScreen.route) {
            ContentColumnComposable(
                onNextButtonClicked = { Actions(navController).onNextButtonScreenOne() }
            )
        }
        composable(AppScreen.ScreenTwo.route) {
            ScreenTwo { navController.navigate(AppScreen.ScreenThree.route) }
        }
        composable(AppScreen.ScreenThree.route) { ScreenThree() }

        // nav drawer destinations
        composable(DrawerItems.Home.route) { HomeScreen() }
        composable(DrawerItems.Inbox.route) { InboxScreen() }
        composable(DrawerItems.Favourites.route) { FavouritesScreen() }
        composable(DrawerItems.NewMessage.route) { NewMessageScreen() }
        composable(DrawerItems.Settings.route) { SettingsScreen() }
        composable(DrawerItems.Trash.route) { TrashScreen() }

        // nav bar destinations
        composable(BottomNavItem.Search.route) { SearchScreen() }
        composable(BottomNavItem.Notifications.route) { NotificationsScreen() }
    }
}

