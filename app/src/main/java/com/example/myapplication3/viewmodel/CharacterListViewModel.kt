package com.example.myapplication3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication3.repository.CharacterListRepository

class CharacterListViewModel(private val repository: CharacterListRepository): ViewModel() {
    val characterListData= repository.characterListData
    val characterDetailData = repository.characterDetailData

    fun getCharacterListData(){
        repository.getCharacterList()
    }

    fun getCharacterDetailData(id: Int){
        repository.getCharacterDetail(id)
    }
}