package com.example.wordapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wordapp.room.Word

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WordDisplay(
    word: Word?,
    displayOption: String,
    onLongPress: (Word) -> Unit = {},
    modifier: Modifier = Modifier
) {
    word?.let { currentWord ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {},
                    onLongClick = { onLongPress(currentWord) }
                )
                .padding(horizontal = 16.dp, vertical = 100.dp)
        ) {
            if (displayOption != "Монгол үгийг харуулах") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(30.dp)
                ) {
                    Text(
                        text = currentWord.foreignWord,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (displayOption != "Зөвхөн гадаад үгийг харуулах") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(30.dp)
                ) {
                    Text(
                        text = currentWord.mongolianWord,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}