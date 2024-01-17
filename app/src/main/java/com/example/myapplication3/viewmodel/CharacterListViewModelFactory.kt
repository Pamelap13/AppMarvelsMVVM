package com.example.myapplication3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication3.repository.CharacterListRepository

class CharacterListViewModelFactory(val repository: CharacterListRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CharacterListViewModel::class.java)){
            return CharacterListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown CharacterListViewModel class")
    }
}