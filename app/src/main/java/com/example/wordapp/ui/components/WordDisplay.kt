package com.example.wordapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.wordapp.room.Word

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WordDisplay(
    word: Word?,
    displayOption: String,
    onLongPress: (Word) -> Unit = {},
    onToggleFavorite: (Word) -> Unit = {},
    modifier: Modifier = Modifier
) {
    word?.let { currentWord ->
        val starTint by animateColorAsState(
            targetValue = if (currentWord.isFavorite) Color.Yellow else MaterialTheme.colorScheme.onSurfaceVariant
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {},
                    onLongClick = { onLongPress(currentWord) }
                )
                .padding(horizontal = 16.dp, vertical = 50.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = if (currentWord.isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = if (currentWord.isFavorite) "Unfavorite" else "Favorite",
                    tint = starTint,
                    modifier = Modifier.clickable { 
                        onToggleFavorite(currentWord.copy(isFavorite = !currentWord.isFavorite))
                    }
                )
            }

            if (displayOption != "Монгол үгийг харуулах") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(20.dp)
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
                        .padding(20.dp)
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