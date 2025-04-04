package com.example.wordapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.wordapp.Repository.Repository
import com.example.wordapp.ViewModel.WordViewModel
import com.example.wordapp.room.WordDB
import com.example.wordapp.ui.components.WordApp
import com.example.wordapp.ui.theme.WordappTheme
import com.example.wordapp.utils.NotificationHelper
private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordappTheme {
                val db = WordDB.getDatabase(LocalContext.current)
                val repository = Repository(db)
                val viewModel = WordViewModel(repository)
                val navController = rememberNavController()

                WordApp(navController, viewModel)
            }
        }
    }
    override fun onDestroy() {

        Log.d(TAG, "Апп -аас гарлаа.")
        NotificationHelper(this).scheduleNotification()
        super.onDestroy()
    }override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }



}