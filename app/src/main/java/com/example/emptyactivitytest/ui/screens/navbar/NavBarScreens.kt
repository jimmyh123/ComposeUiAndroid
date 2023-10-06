package com.example.emptyactivitytest.ui.screens.navbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.emptyactivitytest.ui.screens.navdrawer.BasicScreenTemplate

@Composable
fun SearchScreen() {
    BasicScreenTemplate(title = "Search Screen", colour = Color.Yellow)
}

@Composable
fun NotificationsScreen() {
    BasicScreenTemplate(title = "Notifications Screen", colour = Color.Green)
}