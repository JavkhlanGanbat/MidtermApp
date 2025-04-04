package com.example.wordapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wordapp.ViewModel.WordViewModel

@Composable
fun SettingsScreen(navController: NavController, viewModel: WordViewModel) {
    val selectedOption by viewModel.selectedOption.collectAsState()
    val options = listOf(
        "Монгол болон гадаад үгийг зэрэг харуулах",
        "Зөвхөн гадаад үгийг харуулах",
        "Монгол үгийг харуулах"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Тохиргоо",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                options.forEach { option ->
                    SettingOptionItem(
                        option = option,
                        isSelected = option == selectedOption,
                        onSelected = {
                            viewModel.setSelectedOption(option)
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingOptionItem(
    option: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onSelected)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Check icon (fixed size)
        Box(
            modifier = Modifier.width(24.dp), // Fixed width for icon space
            contentAlignment = Alignment.CenterStart
        ) {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Сонгогдсон",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Text (takes remaining space)
        Text(
            text = option,
            modifier = Modifier.weight(1f), // Takes all remaining space
            style = MaterialTheme.typography.bodyLarge
        )
    }
}