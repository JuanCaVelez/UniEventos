package com.unieventos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.unieventos.ui.navigation.Navigation
import com.unieventos.ui.screens.HomeScreen
import com.unieventos.ui.screens.LoginScreen
import com.unieventos.ui.screens.SignUpScreen
import com.unieventos.ui.theme.UniEventosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            UniEventosTheme {
                Navigation()
            }
        }
    }
}