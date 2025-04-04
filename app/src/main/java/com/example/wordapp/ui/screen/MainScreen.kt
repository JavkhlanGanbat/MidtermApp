package com.example.wordapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wordapp.ViewModel.WordViewModel
import com.example.wordapp.room.Word
import com.example.wordapp.ui.components.WordDisplay
import com.example.wordapp.ui.components.WordEditDialog
import com.example.wordapp.ui.components.WordNavigationButtons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, viewModel: WordViewModel) {
    val words by viewModel.words.collectAsState(initial = emptyList())
    val selectedOption by viewModel.selectedOption.collectAsState()
    var currentIndex by remember { mutableStateOf(0) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var editingWord by remember { mutableStateOf<Word?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Картны апп", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6A1B9A)),
                actions = {
                    IconButton(onClick = { navController.navigate("favoritesScreen") }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Хадгалсан үгс",
                            tint = Color.Yellow
                        )
                    }
                    IconButton(onClick = { navController.navigate("settingsScreen") }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Тохиргоо",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            WordDisplay(
                word = words.getOrNull(currentIndex),
                displayOption = selectedOption,
                onLongPress = { word ->
                    editingWord = word
                    showEditDialog = true
                },
                onToggleFavorite = { updatedWord -> viewModel.updateWord(updatedWord) }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        editingWord = null
                        showEditDialog = true
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00897B))
                ) {
                    Text("Нэмэх")
                }
                Spacer(modifier = Modifier.width(8.dp))
                words.getOrNull(currentIndex)?.let { currentWord ->
                    Button(
                        onClick = {
                            editingWord = currentWord
                            showEditDialog = true
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00897B))
                    ) {
                        Text("Засах")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { showDeleteDialog = true },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                    ) {
                        Text("Устгах")
                    }
                }
            }

            WordNavigationButtons(
                currentIndex = currentIndex,
                wordCount = words.size,
                onPreviousClick = { if (currentIndex > 0) currentIndex-- },
                onNextClick = { if (currentIndex < words.size - 1) currentIndex++ }
            )
        }
    }

    if (showEditDialog) {
        WordEditDialog(
            word = editingWord,
            onSave = { updatedWord ->
                if (editingWord == null) {
                    viewModel.addWord(updatedWord)
                } else {
                    viewModel.updateWord(updatedWord)
                }
                showEditDialog = false
            },
            onCancel = { showEditDialog = false }
        )
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Үг устгах") },
            text = { Text("Та энэ үгийг устгахдаа итгэлтэй байна уу?") },
            confirmButton = {
                Button(
                    onClick = {
                        words.getOrNull(currentIndex)?.let { viewModel.deleteWord(it) }
                        showDeleteDialog = false
                        if (currentIndex >= words.size - 1) {
                            currentIndex = (words.size - 2).coerceAtLeast(0)
                        }
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD32F2F),
                        contentColor = Color.White
                    )
                ) {
                    Text("Устгах")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Болих")
                }
            }
        )
    }
}