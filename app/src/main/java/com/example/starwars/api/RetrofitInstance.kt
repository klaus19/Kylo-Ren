package com.example.starwars.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {
    companion object{
        val api: StarwarsApi by lazy {
            Retrofit.Builder()
                .baseUrl("https://swapi.dev/api")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StarwarsApi::class.java)

    }

    }
}