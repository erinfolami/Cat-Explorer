package com.example.catexplorer.screens.breed.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catexplorer.base.NetworkResult
import com.example.catexplorer.repositories.CatsRepository
import com.example.catexplorer.screens.breed.model.BreedItem
import com.example.catexplorer.screens.breed.model.GetBreeds
import com.example.catexplorer.screens.favourite.model.GetFavouriteItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedSharedViewModel @Inject constructor(private val repository: CatsRepository) : ViewModel(){

    init {
        getBreeds()
    }

    var breedItem  by mutableStateOf<BreedItem?>(null)


    fun addBreedItem(image: BreedItem){
        breedItem = image
    }

    val breeds: MutableState<NetworkResult<GetBreeds>> =
        mutableStateOf(NetworkResult.Loading())


    private fun getBreeds() = viewModelScope.launch {

        repository.getBreeds().collect { values ->
            breeds.value = values
        }
    }

}