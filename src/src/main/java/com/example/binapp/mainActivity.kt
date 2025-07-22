package com.example.binapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.binapp.presentation.navigation.SetupNavigation
import com.example.binapp.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SetupNavigation()
                }
            }
        }
    }
}
/*
Результаты диагноза Помощника ИИ:
Added proper Android and Compose imports
Fixed activity inheritance
Added Hilt entry point annotation
*/
