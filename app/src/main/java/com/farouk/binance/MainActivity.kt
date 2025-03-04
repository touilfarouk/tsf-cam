package com.farouk.binance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.farouk.binance.ui.theme.CustomDrawerTheme
import com.farouk.binance.ui.theme.blue



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CustomDrawerTheme {
                MainScreen(navController)
            }
        }
    }
}
