@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.emptyactivitytest

//import com.example.emptyactivitytest.ui.navigation.DrawerNames
//import com.example.emptyactivitytest.ui.navigation.ScreenNames
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.emptyactivitytest.ui.navigation.BottomNavigation
import com.example.emptyactivitytest.ui.navigation.MyNavHost
import com.example.emptyactivitytest.ui.navigation.NavDrawerContent
import com.example.emptyactivitytest.ui.topbar.TopBarComposable
import com.example.emptyactivitytest.ui.viewmodels.MyViewModel
import com.example.emptyactivitytest.util.TestTags
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyActivityApp(
    navController: NavHostController = rememberNavController()
) {
    val localContext = LocalContext.current
    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val viewModel: MyViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    viewModel.updateScreenName(backStackEntry?.destination?.route)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { NavDrawerContent(navController, localContext, scope, drawerState ) }
        },
    ) {
        Scaffold(
            modifier = Modifier,
            topBar = { TopBarComposable(uiState.currentScreenName, localContext, scope, drawerState) },
            bottomBar = { BottomNavigation(navController) },
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
        ) { innerPadding ->
            MyNavHost(navController, innerPadding)
        }
    }
}

@Composable
fun ScreenTwo(onNextButtonClicked: () -> Unit) {
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
                onClick = { onNextButtonClicked() },
            ) {
                Icon(Icons.Default.Add, contentDescription = "+ Add New ")
                Text(text = "New Note")
            }

            Text(
                modifier = Modifier.testTag(TestTags.LIST_OF_NOTES_TEXT),
                text = "List of Notes",
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column (
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                Text("Hello")
            }
        }
    }
}


fun ScreenThree() {

}