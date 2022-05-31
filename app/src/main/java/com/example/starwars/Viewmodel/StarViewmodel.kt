package com.example.starwars.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.StarRepository.StarwarsRepository
import com.example.starwars.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.Response

class StarViewmodel(private val starwarsRepository: StarwarsRepository):ViewModel() {

       val characters:LiveData<Result> = starwarsRepository.getCharacters()
    /*
    suspend fun getCharacters() = viewModelScope.launch {
         starwarsRepository.getCharacters()
    }*/

        fun getCharacters() {
           val characters:Flow<List<Result>> = starwarsRepository.getCharacters()
       }




}