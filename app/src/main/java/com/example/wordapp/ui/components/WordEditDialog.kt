package com.example.wordapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wordapp.room.Word

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordEditDialog(
    word: Word?,
    onSave: (Word) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var foreignWord by remember { mutableStateOf(word?.foreignWord ?: "") }
    var mongolianWord by remember { mutableStateOf(word?.mongolianWord ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (word == null) "Шинэ үг нэмэх" else "Үг засах") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Буцах")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = foreignWord,
                    onValueChange = { foreignWord = it },
                    label = { Text("Гадаад үг") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = mongolianWord,
                    onValueChange = { mongolianWord = it },
                    label = { Text("Монгол орчуулга") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        val newWord = word?.copy(
                            foreignWord = foreignWord,
                            mongolianWord = mongolianWord
                        ) ?: Word(0, foreignWord, mongolianWord)
                        onSave(newWord)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = foreignWord.isNotEmpty() && mongolianWord.isNotEmpty()
                ) {
                    Text("ХАДГАЛАХ")
                }
            }
        }
    )
}
@Preview(showBackground = true, widthDp = 300, heightDp = 400)
@Composable
fun WordEditDialogPreview_EmptyFields() {
    WordEditDialog(
        word = null,
        onSave = { },
        onCancel = { },
        modifier = Modifier.padding(16.dp)
    )
}