package com.example.emptyactivitytest.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.emptyactivitytest.R
import com.example.emptyactivitytest.ui.viewmodels.MyModel

sealed class BottomNavItem(
    @StringRes val title: Int,
    val route: String,
    var icon: ImageVector
) {
    object HomeBar : BottomNavItem(
        R.string.main_screen,
        NavRoutes.MAIN_SCREEN,
        Icons.Default.Home
    )
    object Search : BottomNavItem(
        R.string.navbar_search,
        NavRoutes.NAVBAR_SEARCH,
        Icons.Default.Search
    )
    object Notifications : BottomNavItem(
        R.string.navbar_notifications,
        NavRoutes.NAVBAR_NOTIFICATIONS,
        Icons.Default.Notifications
    )
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        label = { Text(text = stringResource(id = screen.title)) },
        icon = { screen.icon },
        selected = true,
        alwaysShowLabel = true,
        onClick =  onClick,
        colors = NavigationBarItemDefaults.colors()
    )
}

@Composable
fun BottomNavigation(
    navController: NavController,
) {
    val myModel = MyModel()
    val items = myModel.getNavBarItems()

    NavigationBar {
        items.forEach { item ->
            AddItem(
                onClick = { navController.navigate(item.route) },
                screen = item,
            )
        }
    }

}
