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
import com.example.emptyactivitytest.Screen1
import com.example.emptyactivitytest.Screen2
import com.example.emptyactivitytest.ui.screens.ContentColumnComposable

enum class ScreenNames(@StringRes val title: Int) {
    MAIN_SCREEN(AppScreen.MainScreen.resourceId),
    SCREEN_TWO(AppScreen.ScreenTwo.resourceId),
    SCREEN_THREE(AppScreen.ScreenThree.resourceId)
}

sealed class AppScreen(@StringRes val resourceId: Int, val route: () -> String) {
    object MainScreen : AppScreen(R.string.main_screen, { ScreenNames.MAIN_SCREEN.name })
    object ScreenTwo : AppScreen(R.string.screen_two, { ScreenNames.SCREEN_TWO.name })
    object ScreenThree : AppScreen(R.string.screen_three, { ScreenNames.SCREEN_THREE.name })
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

@Composable
fun MyNavHost(navController: NavHostController, innerPadding: PaddingValues) {

    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = AppScreen.MainScreen.route()) {
        composable(AppScreen.MainScreen.route()) {
            ContentColumnComposable(
                onNextButtonClicked = { Actions(navController).onNextButtonScreenOne() }
            )
        }
        composable(AppScreen.ScreenTwo.route()) {
            Screen1 { navController.navigate(AppScreen.ScreenThree.route()) }
        }
        composable(AppScreen.ScreenThree.route()) { Screen2() }
    }
}

