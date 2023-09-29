@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.emptyactivitytest.ui

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.emptyactivitytest.Android
import com.example.emptyactivitytest.R
import com.example.emptyactivitytest.TestTags
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComposable(currentScreen: Int, contextForToast: Context) {

    val screenTitle = contextForToast.getString(currentScreen)
    CenterAlignedTopAppBar(
        title = {
            Text(text = screenTitle)
                },
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
fun EmptyActivityApp(
    navController: NavHostController = rememberNavController()
) {
    val contextForToast = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = ScreenNames.valueOf(
            backStackEntry?.destination?.route ?: AppScreen.MainScreen.route()
    ).title



    Scaffold(
        modifier = Modifier,
        topBar = { TopBarComposable(currentScreen, contextForToast) },
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

        MyNavHost(navController, innerPadding)

    }
}

@Composable
fun MyNavHost(navController: NavHostController, innerPadding: PaddingValues) {

    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = AppScreen.MainScreen.route()) {
        composable(AppScreen.MainScreen.route()) {
            ContentColumnComposable(
                onNextButtonClicked = { navController.navigate(AppScreen.ScreenTwo.route()) }
            )
        }
        composable(AppScreen.ScreenTwo.route()) {
            Screen1 { navController.navigate(AppScreen.ScreenThree.route()) }
        }
        composable(AppScreen.ScreenThree.route()) { Screen2() }
    }
}



fun Screen2() {

}

@Composable
fun ContentColumnComposable(onNextButtonClicked: () -> Unit) {

    val contextForToast = LocalContext.current

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

@Composable
fun Screen1(onNextButtonClicked: () -> Unit) {
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

            Text(
                modifier = Modifier.testTag(TestTags.LIST_OF_NOTES_GOES_HERE_TEXT),
                text = "List of Notes Goes here",
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

class Actions(navController: NavHostController) {
    val onNextButtonScreenOne: () -> Unit = {
        navController.navigate(AppScreen.ScreenTwo.route())
    }
//    val openTask: (Int) -> Unit = { taskId ->
//        navController.navigate("$TaskDetail/$taskId")
//    }
//    val addTask: () -> Unit = {
//        navController.navigate(AddTask)
//    }
//    val addProject: () -> Unit = {
//        navController.navigate(AddProject)
//    }
//    val navigateBack: () -> Unit = {
//        navController.popBackStack()
//    }
}


//enum class ScreenNames(@StringRes val title: Int) {
//enum class ScreenNames(val type: String) {
//    screen_one(AppScreen.ScreenOne.title),
//    screen_two(AppScreen.ScreenTwo.title),
//    screen_three(AppScreen.ScreenThree.title)
//}

enum class ScreenNames(@StringRes val title: Int) {
    MAIN_SCREEN(AppScreen.MainScreen.resourceId),
    SCREEN_TWO(AppScreen.ScreenTwo.resourceId),
    SCREEN_THREE(AppScreen.ScreenThree.resourceId)
}


typealias ComposableFun = @Composable () -> Unit
sealed class AppScreen(@StringRes val resourceId: Int, val route: () -> String, var screen: ComposableFun) {
    object MainScreen : AppScreen(R.string.main_screen, { ScreenNames.MAIN_SCREEN.name },{}
//        { ContentColumnComposable(Actions(navController = ).onNextButtonScreenOne) }
    )
    object ScreenTwo : AppScreen(R.string.screen_two, { ScreenNames.SCREEN_TWO.name }, { })
    object ScreenThree : AppScreen(R.string.screen_three, { ScreenNames.SCREEN_THREE.name }, { })
}
