package com.example.wordapp.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wordapp.ViewModel.WordViewModel
import com.example.wordapp.ui.screen.MainScreen
import com.example.wordapp.ui.screen.SettingsScreen

@Composable
fun WordApp(navController: NavHostController, viewModel: WordViewModel) {
    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ) {
        composable("mainScreen") {
            MainScreen(navController, viewModel)
        }
        composable("settingsScreen") {
            SettingsScreen(navController, viewModel)
        }
    }
}