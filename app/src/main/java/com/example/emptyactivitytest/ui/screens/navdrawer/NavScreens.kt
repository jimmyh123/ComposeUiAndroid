package com.example.emptyactivitytest.ui.screens.navdrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.emptyactivitytest.util.RenderPreview

// temporary holder for all screens
@Composable
fun BasicScreenTemplate(title: String, colour: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colour),
        contentAlignment = Alignment.Center,

        ){
        Text(title)
    }
}

@Composable
fun HomeScreen() {
    BasicScreenTemplate(title = "Home Screen", colour = Color.Blue)
}

@Composable
fun InboxScreen() {
    BasicScreenTemplate(title = "Inbox Screen", colour = Color.Yellow)
}

@Composable
fun FavouritesScreen() {
    BasicScreenTemplate(title = "Favourites Screen", colour = Color.Green)
}

@Composable
fun NewMessageScreen() {
    BasicScreenTemplate(title = "New Message Screen", colour = Color.LightGray)
}

@Composable
fun SettingsScreen() {
    BasicScreenTemplate(title = "Settings Screen", colour = Color.Red)
}

@Composable
fun TrashScreen() {
    BasicScreenTemplate(title = "Trash Screen", colour = Color.Green)
}

@Preview
@Composable
fun HomeScreenPreview() = RenderPreview {
    HomeScreen()
}