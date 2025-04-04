package com.example.wordapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wordapp.ViewModel.WordViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController, viewModel: WordViewModel) {
    val selectedOption by viewModel.selectedOption.collectAsState()
    val options = listOf(
        "Монгол болон гадаад үгийг зэрэг харуулах",
        "Зөвхөн гадаад үгийг харуулах",
        "Монгол үгийг харуулах"
    )
  
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Тохиргоо", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Буцах", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6A1B9A))
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
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
                    Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)
                }
            }
        }
    )
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
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = option,
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        if (isSelected) {
            Text(
                text = "✓",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary
            )
        }
    }
}