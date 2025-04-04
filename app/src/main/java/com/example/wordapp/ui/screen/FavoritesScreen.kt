package com.example.wordapp.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.wordapp.ViewModel.WordViewModel
import com.example.wordapp.ui.components.WordDisplay
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavHostController, viewModel: WordViewModel) {
    val words by viewModel.words.collectAsState(initial = emptyList())
    val favoriteWords = words.filter { it.isFavorite }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Хадгалсан үгс") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Буцах")
                    }
                }
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(favoriteWords) { word ->
                    WordDisplay(
                        word = word,
                        displayOption = "Монгол болон гадаад үг",
                        onToggleFavorite = { updatedWord -> viewModel.updateWord(updatedWord) },
                        onLongPress = { /* ...existing behavior... */ }
                    )
                }
            }
        }
    )
}
