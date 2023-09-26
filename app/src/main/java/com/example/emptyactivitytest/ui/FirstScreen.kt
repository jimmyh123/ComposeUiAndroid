package com.example.emptyactivitytest.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emptyactivitytest.Android
import com.example.emptyactivitytest.Dimensions
import com.example.emptyactivitytest.OverlappingRow
import com.example.emptyactivitytest.R

@Composable
fun AndroidAlien(
    colours: List<Color> = emptyList(),
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column {
            OverlappingRow(
                overlapFactor = 0.5f
            ) {
                for (i in colours.indices) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = modifier,
                        alignment = Alignment.CenterStart,
                        contentScale = ContentScale.Fit,
                        alpha = 0.8F,
                        colorFilter = ColorFilter.tint(color = colours[i]),
                    )
                }
//                Image(
//                    painter = painterResource(R.drawable.ic_launcher_foreground),
//                    contentDescription = null,
//                    modifier = modifier,
//                    alignment = Alignment.CenterStart,
//                    contentScale = ContentScale.Fit,
//                    alpha = 0.8F,
//                    colorFilter = ColorFilter.tint(color = color),
//                )
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowerCardSingle(
    contextForToast: Context?,
    android: Android
) {
    Card(
        onClick = {
            contextForToast?.let {
                safeContext -> Toast.makeText(safeContext, android.nickname, Toast.LENGTH_SHORT).show()
            }
          },
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .padding(0.dp)
            .width(300.dp),

    ) {
        Row(modifier = Modifier.padding(start = 20.dp, bottom = 20.dp)) {
            Column {
                AndroidAlien(android.colours)
                Text(
                    text = android.name ?: "Name unknown",
                    modifier = Modifier.padding(start = Dimensions.androidTextPaddingStart),
                    style = TextStyle(
                        fontSize = 16.sp
                    ),
                )
                Text(
                    text = android.nickname ?: "Nickname Unknown",
                    modifier = Modifier.padding(start = Dimensions.androidTextPaddingStart),
                    style = TextStyle(
                        fontSize = 12.sp
                    ),
                )
            }
        }
    }
}

@Composable
fun FlowerCardDouble(
//    flower: Flowers
){
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .padding(10.dp)
            .width(360.dp),
    ) {
        Row(modifier = Modifier.padding(20.dp)) {

            Column {
                Row(modifier = Modifier.fillMaxWidth()){
                    AndroidAlien(listOf(Color.Blue))
                    AndroidAlien(listOf(Color.Red))
                }
                Text(
                    text = "android.name",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = "android.nickname",
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}

@Composable
fun FlowerSurface() {
    Column {
        Surface(
            modifier = Modifier
                .size(300.dp)
                .padding(20.dp),
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(32.dp),
            shadowElevation = 16.dp
        ) {
            Row(modifier = Modifier.padding(20.dp)) {
                Column {
                    AndroidAlien(listOf(Color.Yellow))
                    Text(
                        text = "android.name",
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = "android.nickname",
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AndroidAlienPreview() = RenderPreview {
    AndroidAlien(listOf(Color.Blue))
}


@Preview
@Composable
fun FlowerCardSinglePreview() = RenderPreview {
    FlowerCardSingle(
        null,
        Android("James", "Jimmy", listOf(Color.Blue))
    )
}

@Preview
@Composable
fun FlowerCardDoublePreview() = RenderPreview {
    FlowerCardDouble()
}

@Preview
@Composable
fun FlowerSurfacePreview() = RenderPreview {
    FlowerSurface()
}