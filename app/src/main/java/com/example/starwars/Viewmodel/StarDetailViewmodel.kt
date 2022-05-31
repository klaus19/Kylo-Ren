package com.example.starwars.Viewmodel

import androidx.lifecycle.*
import com.example.starwars.Utils.Resource
import com.example.starwars.model.HomeWorldResponse
import com.example.starwars.model.Result
import com.example.starwars.repository.StarwarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StarDetailViewmodel @Inject constructor(private val starwarsRepository: StarwarsRepository,
      savedStateHandle: SavedStateHandle):
             ViewModel(){

                 private val myArguments = savedStateHandle.get<Result>("character details")

         private val _details = MutableLiveData<Result>()
        val details : LiveData<Result>
          get() = _details

         private val _homeworld = MutableStateFlow<Resource<HomeWorldResponse>>(Resource.Empty())
           val homeworld: StateFlow<Resource<HomeWorldResponse>>
             get() = _homeworld

         init {
             _details.value = myArguments!!
             getHomeworldData(myArguments.homeworld)
         }

    private fun getHomeworldData(homeworld: String) {
        viewModelScope.launch(Dispatchers.IO) {
              _homeworld.value = Resource.Loading()
            when (val homeworld = starwarsRepository.getHomeworld(homeworld)){
                is Resource.Failure ->{
                    _homeworld.value = Resource.Failure(homeworld.message!!)

                }
                is Resource.Success ->{
                    if(homeworld.data == null){
                        _homeworld.value = Resource.Failure("N/A")
                    }else{
                        _homeworld.value = Resource.Success(homeworld.data)
                    }
                }
            }

        }

    }

}