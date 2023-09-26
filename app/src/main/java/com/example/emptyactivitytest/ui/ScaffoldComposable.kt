@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.emptyactivitytest.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.emptyactivitytest.Android
import com.example.emptyactivitytest.TestTags
import com.example.emptyactivitytest.ui.theme.EmptyActivityTestTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComposable(contextForToast: Context) {

    CenterAlignedTopAppBar(
        title = { Text(text = "Sandbox App") },
        navigationIcon = {
            IconButton(
                modifier = Modifier.testTag(TestTags.NAVIGATION_BAR_BUTTON),
                onClick = { Toast.makeText(contextForToast, "Nav Icon Click", Toast.LENGTH_SHORT).show() },
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(
                modifier = Modifier.testTag(TestTags.TOOLBAR_PLUS_SIGN),
                onClick = { Toast.makeText(contextForToast, "Add Click", Toast.LENGTH_SHORT).show()},
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Items")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Green.copy(alpha = 0.3f)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposable() {
    EmptyActivityTestTheme {

        val contextForToast = LocalContext.current
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        Scaffold(
            modifier = Modifier,
            topBar = { TopBarComposable(contextForToast) },
            bottomBar = { BottomBarComposable() },
            snackbarHost = { SnackbarHost(snackbarHostState) },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                var clickCount by remember { mutableStateOf(0) }
                ExtendedFloatingActionButton(
                    modifier = Modifier.testTag(TestTags.SHOW_SNACKBAR_BUTTON),
                    onClick = {
                        // show snackbar as a suspend function
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Snackbar # ${++clickCount}"
                            )
                        }
                    }
                ) { Text("Show snackbar") }
            },
            containerColor = Color.Blue,
            contentColor = Color.Red,
            // contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                ContentColumnComposable(contextForToast)
            }
        }
    }
}

@Composable
fun ContentColumnComposable(contextForToast: Context) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Button(
                modifier = Modifier
                    .align(Alignment.End)
                    .testTag(TestTags.NEW_NOTE_BUTTON),
                onClick = {},
            ) {
                Icon(Icons.Default.Add, contentDescription = "+ Add New ")
                Text(text = "New Note")
            }

            Text(
                modifier = Modifier.testTag(TestTags.LIST_OF_NOTES_TEXT),
                text = "List of Notes",
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.testTag(TestTags.LIST_OF_NOTES_GOES_HERE_TEXT),
                text = "List of Notes Goes here",
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column (
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                FlowerCardSingle(contextForToast, Android("James","Jimmy", listOf(Color.Blue)) )
                FlowerCardSingle(contextForToast, Android("William","Willy", listOf(Color.Black, Color.Red)))
                FlowerCardSingle(contextForToast, Android("Edward","Ed", listOf(Color.Yellow)))
                FlowerCardSingle(contextForToast, Android("Christopher","Chris", listOf(Color.Green, Color.Red)))
            }
        }
    }
}

@Composable
fun BottomBarComposable() {
    BottomAppBar(
        modifier =  Modifier.testTag(TestTags.BOTTOM_APP_BAR),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Bottom app bar",
        )
    }
}