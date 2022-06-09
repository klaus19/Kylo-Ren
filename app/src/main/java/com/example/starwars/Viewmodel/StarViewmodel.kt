package com.example.starwars.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.starwars.model.Result
import com.example.starwars.repository.StarwarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class StarViewmodel @Inject constructor(private val starwarsRepository: StarwarsRepository):
    ViewModel() {

   fun getCharacters(search: String):Flow<PagingData<Result>>{
       return  starwarsRepository.getCharacters(search)
   }








}