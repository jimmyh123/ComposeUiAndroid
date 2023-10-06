package com.example.emptyactivitytest.ui.navigation

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.emptyactivitytest.R
import com.example.emptyactivitytest.ui.viewmodels.MyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class DrawerItems(
    @StringRes val title: Int,
    val route: String,
    val icon: ImageVector
) {
    object Home : DrawerItems(
        R.string.navdrawer_title_home,
        NavRoutes.NAVDRAWER_HOME,
        Icons.Default.Home
    )
    object Inbox : DrawerItems(
        R.string.navdrawer_title_inbox,
        NavRoutes.NAVDRAWER_INBOX,
        Icons.Default.Email
    )
    object Favourites : DrawerItems(
        R.string.navdrawer_title_favourites,
        NavRoutes.NAVDRAWER_FAVOURITES,
        Icons.Default.Favorite
    )
    object NewMessage : DrawerItems(
        R.string.navdrawer_title_new_message,
        NavRoutes.NAVDRAWER_NEW_MESSAGE,
        Icons.Default.Create
    )
    object Settings : DrawerItems(
        R.string.navdrawer_title_settings,
        NavRoutes.NAVDRAWER_SETTINGS,
        Icons.Default.Settings
    )
    object Trash : DrawerItems(
        R.string.navdrawer_title_trash,
        NavRoutes.NAVDRAWER_TRASH,
        Icons.Default.Delete
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawerContent(
    navController: NavController,
    localContext: Context,
    scope: CoroutineScope,
    drawerState: DrawerState,
) {
    val myModel = MyModel()
    val items = myModel.getNavDrawerItems()

    Text(stringResource(R.string.nav_drawer_title), modifier = Modifier.padding(16.dp))
    Divider()

    items.forEach {
        NavigationDrawerItem(
            label = { Text(text = localContext.getString(it.title)) },
            selected = false,
            onClick = {
                navController.navigate(it.route)
                scope.launch {
                    drawerState.apply {
                        if (isOpen) close()
                    }
                }
            },
            icon = { Icon(it.icon, contentDescription = it.title.toString()) }
        )
    }
}