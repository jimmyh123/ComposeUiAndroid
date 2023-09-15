@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.emptyactivitytest.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.example.emptyactivitytest.Greeting
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComposable() {
    val contextForToast = LocalContext.current

    CenterAlignedTopAppBar(
        title = { Text(text = "SemicolonSpace") },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(contextForToast, "Nav Icon Click", Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = {
                Toast.makeText(contextForToast, "Add Click", Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Items")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = androidx.compose.ui.graphics.Color.Green.copy(alpha = 0.3f)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposable() {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier, // modifier: Modifier = Modifier,
        topBar = { TopBarComposable() }, // topBar: @Composable () -> Unit = {},
        bottomBar = { BottomBarComposable() },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            var clickCount by remember { mutableStateOf(0) }
            ExtendedFloatingActionButton(
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
        containerColor = androidx.compose.ui.graphics.Color.Blue, // containerColor: Color = MaterialTheme.colorScheme.background,
        contentColor = androidx.compose.ui.graphics.Color.Red, // contentColor: Color = contentColorFor(containerColor),
        // contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    ) {
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ){
            ContentComposable()
        }
    }
}

@Composable
fun ContentComposable() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting("Android")
    }
}

@Composable
fun BottomBarComposable() {
    BottomAppBar(
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