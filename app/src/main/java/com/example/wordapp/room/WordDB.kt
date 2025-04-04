package com.example.wordapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDB : RoomDatabase() {
    abstract fun wordAppDao(): WordAppDao

    companion object {
        @Volatile
        private var INSTANCE: WordDB? = null

        fun getDatabase(context: Context): WordDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDB::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}