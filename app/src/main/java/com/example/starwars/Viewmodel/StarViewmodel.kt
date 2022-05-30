package com.example.starwars.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.StarRepository.StarwarsRepository
import kotlinx.coroutines.launch

class StarViewmodel(private val starwarsRepository: StarwarsRepository):ViewModel() {

    suspend fun getCharacters() = viewModelScope.launch {
        val character_names = starwarsRepository.getCharacters()
    }




}