package com.example.wordapp.Repository

import com.example.wordapp.room.Word
import com.example.wordapp.room.WordDB
import kotlinx.coroutines.flow.Flow

class Repository(private val WordDB:WordDB) {
    suspend fun  addWord(word: Word){
        WordDB.wordAppDao().addWord(word)
    }
    suspend fun  updateWord(word: Word){
        WordDB.wordAppDao().updateWord(word)
    }

    suspend fun  deleteWord(word: Word){
        WordDB.wordAppDao().deleteWord(word)
    }

    fun getAllWordsFromRoom():Flow<List<Word>>{
        return  WordDB.wordAppDao().getAllWords()
    }
}