package com.example.wordapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wordapp.ViewModel.WordViewModel
import com.example.wordapp.room.Word

@Composable
fun WordInputForm(
    viewModel: WordViewModel,
    selectedWord: Word?,
    onForeignChange: (String) -> Unit,
    onMongolianChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var inputForeign by remember { mutableStateOf(selectedWord?.foreignWord ?: "") }
    var inputMongolian by remember { mutableStateOf(selectedWord?.mongolianWord ?: "") }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = inputForeign,
            onValueChange = {
                inputForeign = it
                onForeignChange(it)
            },
            label = { Text("Гадаад үг") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = inputMongolian,
            onValueChange = {
                inputMongolian = it
                onMongolianChange(it)
            },
            label = { Text("Монгол орчуулга") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (inputForeign.isNotEmpty() && inputMongolian.isNotEmpty()) {
                        viewModel.addWord(Word(0, inputForeign, inputMongolian))
                        inputForeign = ""
                        inputMongolian = ""
                    }
                }
            ) {
                Text("Нэмэх")
            }
            Button(
                onClick = {
                    selectedWord?.let {
                        viewModel.updateWord(Word(it.id, inputForeign, inputMongolian))
                        inputForeign = ""
                        inputMongolian = ""
                    }
                },
                enabled = selectedWord != null
            ) {
                Text("Шинэчлэх")
            }
            Button(
                onClick = {
                    selectedWord?.let {
                        viewModel.deleteWord(it)
                        inputForeign = ""
                        inputMongolian = ""
                    }
                },
                enabled = selectedWord != null
            ) {
                Text("Устгах")
            }
        }
    }
}