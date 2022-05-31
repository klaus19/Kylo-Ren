package com.example.starwars.repository

import com.example.starwars.api.RetrofitInstance
import com.example.starwars.api.StarwarsAdapter
import com.example.starwars.api.StarwarsApi
import com.example.starwars.model.Result
import retrofit2.Response

class StarwarsRepository(private val starwarsApi: StarwarsApi,
                         private val starwarsAdapter: StarwarsAdapter):Repository {


    suspend fun getCharacters():Response<List<Result>> = RetrofitInstance.api.getCharacters()



}