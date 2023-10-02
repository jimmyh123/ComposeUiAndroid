package com.example.emptyactivitytest.util

import androidx.compose.runtime.Composable
import com.example.emptyactivitytest.ui.theme.EmptyActivityTestTheme

@Composable
fun RenderPreview(content: @Composable () -> Unit) = EmptyActivityTestTheme(content = content)

