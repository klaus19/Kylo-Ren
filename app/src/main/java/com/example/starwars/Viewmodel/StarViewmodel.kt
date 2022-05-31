package com.example.starwars.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.starwars.repository.StarwarsRepository
import com.example.starwars.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.Response
import javax.inject.Inject

class StarViewmodel @Inject constructor(private val starwarsRepository: StarwarsRepository):
    ViewModel() {

   fun getCharacters(search: String):Flow<PagingData<Result>>{
       return  starwarsRepository.getCharacters(search)
   }








}