package com.example.wordapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val foreignWord:String,
    val mongolianWord: String
)
