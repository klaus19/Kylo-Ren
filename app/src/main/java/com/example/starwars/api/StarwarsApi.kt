package com.example.starwars.api

import com.example.starwars.model.HomeWorldResponse
import com.example.starwars.model.Starwars
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("people/?page/")
    suspend fun  getCharacters(@Query("page")page: Int):Starwars

    @GET
    suspend fun getHomeworld(@Url url:String): HomeWorldResponse


}