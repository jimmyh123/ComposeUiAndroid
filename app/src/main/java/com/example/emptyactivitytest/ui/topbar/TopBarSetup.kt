package com.example.emptyactivitytest.ui.topbar

import android.content.Context
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.emptyactivitytest.util.TestTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComposable(
    currentScreen: Int,
    contextForToast: Context,
    scope: CoroutineScope,
    drawerState: DrawerState
) {

    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = currentScreen))
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.testTag(TestTags.NAVIGATION_BAR_BUTTON),
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                },
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