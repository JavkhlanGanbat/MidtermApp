package com.example.wordapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WordNavigationButtons(
    currentIndex: Int,
    wordCount: Int,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Button(
            onClick = onPreviousClick,
            enabled = currentIndex > 0,
            modifier = Modifier.weight(1f)
        ) {
            Text("Өмнөх")
        }

        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        Button(
            onClick = onNextClick,
            enabled = currentIndex < wordCount - 1,
            modifier = Modifier.weight(1f)
        ) {
            Text("Дараах")
        }
    }
}