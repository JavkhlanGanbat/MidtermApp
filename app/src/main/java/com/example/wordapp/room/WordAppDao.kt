package com.example.wordapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WordAppDao {
    @Query("SELECT * FROM words")
    fun getAllWords():Flow<List<Word>>

    @Insert
    suspend fun addWord(word: Word)

    @Update
    suspend fun  updateWord(word: Word)

    @Delete
    suspend fun  deleteWord(word: Word)
}