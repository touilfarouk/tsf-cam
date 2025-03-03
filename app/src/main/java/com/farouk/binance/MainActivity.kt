package com.farouk.binance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import com.farouk.binance.ui.theme.CustomDrawerTheme
import com.farouk.binance.ui.theme.blue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {

            CustomDrawerTheme {
                MainScreen() // Your main UI
            }
        }
    }
}
