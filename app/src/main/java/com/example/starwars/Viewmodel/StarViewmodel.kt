package com.example.starwars.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.repository.StarwarsRepository
import com.example.starwars.model.Result
import kotlinx.coroutines.launch
import okhttp3.Response

class StarViewmodel(private val starwarsRepository: StarwarsRepository):ViewModel() {



    suspend fun getCharacters() = viewModelScope.launch {
        val characters:LiveData<Response<List<Result>>> = starwarsRepository.getCharacters()
    }






}