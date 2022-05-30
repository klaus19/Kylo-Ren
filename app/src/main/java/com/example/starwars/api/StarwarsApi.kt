package com.example.starwars.api

import com.example.starwars.model.Result
import com.example.starwars.model.Starwars
import retrofit2.Response
import retrofit2.http.GET

interface StarwarsApi {

    @GET("/people")
    suspend fun  getCharacters():Response<List<Result>>
}