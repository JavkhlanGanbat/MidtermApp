package com.example.wordapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        val screenHeight = configuration.screenHeightDp.dp
        val isLandscape = screenWidth > screenHeight

        if (isLandscape) {
            Row(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
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
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .widthIn(max = screenWidth * 0.4f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            editingWord = null
                            showEditDialog = true
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00897B)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Нэмэх")
                    }

                    words.getOrNull(currentIndex)?.let { currentWord ->
                        Button(
                            onClick = {
                                editingWord = currentWord
                                showEditDialog = true
                            },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00897B)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Засах")
                        }

                        Button(
                            onClick = { showDeleteDialog = true },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Устгах")
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    WordNavigationButtons(
                        currentIndex = currentIndex,
                        wordCount = words.size,
                        onPreviousClick = { if (currentIndex > 0) currentIndex-- },
                        onNextClick = { if (currentIndex < words.size - 1) currentIndex++ }
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
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
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            editingWord = null
                            showEditDialog = true
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00897B)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Нэмэх")
                    }

                    words.getOrNull(currentIndex)?.let { currentWord ->
                        Button(
                            onClick = {
                                editingWord = currentWord
                                showEditDialog = true
                            },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00897B)),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Засах")
                        }

                        Button(
                            onClick = { showDeleteDialog = true },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                            modifier = Modifier.weight(1f)
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

@Composable
fun Modifier.wrapContentWidth(): Modifier = this