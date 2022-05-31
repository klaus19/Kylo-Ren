package com.example.starwars.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwars.Utils.CharactersPagingSource
import com.example.starwars.api.ApiService
import com.example.starwars.model.Result
import com.example.starwars.model.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StarwarsRepository @Inject constructor(private val starwarsApi: ApiService) : SafeApiCall() {


    fun getCharacters(search: String):Flow<PagingData<Result>>{
        return Pager(
            config = PagingConfig(
                pageSize =30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                     CharactersPagingSource(starwarsApi,search)
            }
        ).flow
    }

    suspend fun getHomeworld(url:String) = safeApiCall {
        starwarsApi.getHomeworld(url)
    }



}