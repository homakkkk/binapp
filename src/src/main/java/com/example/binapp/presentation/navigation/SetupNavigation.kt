package com.example.binapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.binapp.presentation.screens.FirstScreen
import com.example.binapp.presentation.screens.SecondScreen

/**
 * Настройки навигации для приложения.
 *
 * Устанавливает пути для первого и второго экранов.
 */
@Composable
fun SetupNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen") {
        composable(route = "first_screen") {
            FirstScreen(navController = navController)
        }
        composable(route = "second_screen") {
            SecondScreen(navController = navController)
        }
    }
}