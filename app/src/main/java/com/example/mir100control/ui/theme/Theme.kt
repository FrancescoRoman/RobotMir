package com.example.mir100control.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

private val DarkColors = darkColorScheme(
    primary = Color(0xFF1E88E5),
    onPrimary = Color.White,
    secondary = Color(0xFF64B5F6),
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    error = Color(0xFFEF5350)
)

@Composable
fun MiR100ControlTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = Typography(),
        content = content
    )
}
