package com.example.emptyactivitytest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.emptyactivitytest.Android
import com.example.emptyactivitytest.ui.FlowerCardSingle
import com.example.emptyactivitytest.util.TestTags

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