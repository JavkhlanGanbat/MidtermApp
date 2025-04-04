package com.example.wordapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordapp.Repository.Repository
import com.example.wordapp.room.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WordViewModel(private val repository: Repository): ViewModel() {
val words: Flow<List<Word>> = repository.getAllWordsFromRoom()
    private val _selectedOption = MutableStateFlow("Хоёуланг нь харуулах")
    val selectedOption: StateFlow<String> = _selectedOption
    fun setSelectedOption(option:String){
        _selectedOption.value=option
    }


    fun addWord(word: Word){
        viewModelScope.launch{
            repository.addWord(word)
        }
    }


    fun updateWord(word: Word){
        viewModelScope.launch {
            repository.updateWord(word)
        }
    }

    fun deleteWord(word: Word){
        viewModelScope.launch {
            repository.deleteWord(word)
        }
    }
}