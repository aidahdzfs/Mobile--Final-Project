package com.D121211037.filmapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.D121211037.filmapp.R


val AbrilFatface = FontFamily(
    Font(R.font.abril_atface_regular)
)

val Firasans = FontFamily(
    Font(R.font.firasans_regular),
    Font(R.font.firasans_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic)
)
// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Firasans,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Firasans,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 30.sp
    ),
    titleMedium = TextStyle(
        fontFamily = AbrilFatface,
        fontSize = 30.sp
    ),
    titleSmall = TextStyle(
        fontFamily = AbrilFatface,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Firasans,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = TextStyle(
        fontFamily = Firasans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)