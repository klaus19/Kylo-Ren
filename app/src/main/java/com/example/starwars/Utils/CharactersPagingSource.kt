package com.example.starwars.Utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwars.api.ApiService
import com.example.starwars.model.Result


class CharactersPagingSource(private val apiService: ApiService, private val searchString: String) :
    PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: 1
        return try {
                val response = apiService.getCharacters(position)
            val characters = response.results
            val filterData = characters.filter { it.name.contains(searchString,true) }
            val nextKey = position+1
            val prevKey = if (position==1)null else position-1
            LoadResult.Page(data=filterData,prevKey = prevKey,nextKey=nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }
    }