package com.example.emptyactivitytest.ui.viewmodels

import com.example.emptyactivitytest.ui.navigation.AppScreen
import com.example.emptyactivitytest.ui.navigation.BottomNavItem
import com.example.emptyactivitytest.ui.navigation.DrawerItems

class MyModel {

    fun getAppScreenItems() = listOf( // list of screens // todo decide whether to stick with this semi hardcoding or not
        AppScreen.MainScreen,
        AppScreen.ScreenTwo,
        AppScreen.ScreenThree
    )

    fun getNavDrawerItems() = listOf(
        DrawerItems.Home,
        DrawerItems.Inbox,
        DrawerItems.Favourites,
        DrawerItems.NewMessage,
        DrawerItems.Settings,
        DrawerItems.Trash,
    )

    fun getNavBarItems() = listOf(
        BottomNavItem.HomeBar,
        BottomNavItem.Search,
        BottomNavItem.Notifications,
    )

    fun getCurrentScreen(route: String?): Int {

        val potentialScreenName = getAppScreenItems()
            .filter { it.route == route }

        val potentialNavDrawerItemName = getNavDrawerItems()
            .filter{ it.route == route }

        val potentialNavBarItemName = getNavBarItems()
            .filter { it.route == route }

        with(potentialScreenName) { if (isNotEmpty()){ return first().title}}
        with(potentialNavDrawerItemName) { if (isNotEmpty()){ return first().title}}
        with(potentialNavBarItemName) { if (isNotEmpty()){ return first().title}}
        return AppScreen.MainScreen.title
    }
}