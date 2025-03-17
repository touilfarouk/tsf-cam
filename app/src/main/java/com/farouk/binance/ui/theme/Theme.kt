package com.farouk.binance.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF0A192F), // Very Dark Blue
    secondary = Color(0xFF1B2A41), // Dark Blue
    tertiary = Color(0xFF324A5F), // Muted Teal Blue

    // Other colors
    background = Color(0xFF071A2F),  // Dark Navy Blue
    surface = Color(0xFF0A192F),  // Deep Blue
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFDCE3E9),  // Light Blue-Gray
    onSurface = Color(0xFFC5D1E0)  // Soft Cool Gray-Blue
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFFFFF), // Pure White
    secondary = Color(0xFFF7F9FC), // Very Light Gray-Blue
    tertiary = Color(0xFFE1E8F0), // Soft Cool Gray

    // Other colors
    background = Color(0xFFFFFFFF),  // Pure White
    surface = Color(0xFFEEF2F5),  // Light Gray-Blue
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color(0xFF0A192F),  // Very Dark Blue
    onSurface = Color(0xFF1B2A41)  // Dark Blue
)

// Binance themes
/*
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFF3BA2F), // Binance Yellow
    secondary = Color(0xFF181A20), // Binance Dark Gray
    tertiary = Color(0xFF1E2329), // Binance Slightly Lighter Gray

    background = Color(0xFF0B0E11),  // Binance Black
    surface = Color(0xFF181A20),  // Binance Dark Gray
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFFDCE3E9),  // Light Blue-Gray
    onSurface = Color(0xFFC5D1E0)  // Soft Cool Gray-Blue
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF3BA2F), // Binance Yellow
    secondary = Color(0xFFFAFAFA), // Light Gray
    tertiary = Color(0xFFEAECEF), // Soft Gray

    background = Color(0xFFFFFFFF),  // White
    surface = Color(0xFFF5F7FA),  // Light Gray
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color(0xFF0B0E11),  // Binance Black
    onSurface = Color(0xFF181A20)  // Binance Dark Gray
)
*/


@Composable
fun CustomDrawerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val statusBarColor = if (darkTheme) Color(0xFF0A192F) else Color.White
            val navigationBarColor = if (darkTheme) Color(0xFF0A192F) else Color.White

            window.statusBarColor = statusBarColor.toArgb()  // Set Status Bar Color
            window.navigationBarColor = navigationBarColor.toArgb() // Set Navigation Bar Color

            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = !darkTheme
            insetsController.isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
