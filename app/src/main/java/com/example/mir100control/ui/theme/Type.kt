package com.example.mir100control.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.mir100control.R

val montserrat = FontFamily(Font(R.font.montserrat_regular))

val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = montserrat,
        fontSize = 16.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = montserrat,
        fontSize = 20.sp
    )
)
