package com.example.wordapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Word::class], version = 2, exportSchema = false)
abstract class WordDB : RoomDatabase() {
    abstract fun wordAppDao(): WordAppDao

    companion object {
        @Volatile
        private var INSTANCE: WordDB? = null
        
        // Migration from version 1 to 2: add isFavorite column with default false.
        private val MIGRATION_1_2 = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE words ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getDatabase(context: Context): WordDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDB::class.java,
                    "word_database"
                )
                .addMigrations(MIGRATION_1_2)
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}