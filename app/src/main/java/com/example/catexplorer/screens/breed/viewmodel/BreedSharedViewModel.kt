package com.example.catexplorer.screens.breed.viewmodel

import android.util.Log
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
import com.example.catexplorer.screens.wallpapers.model.CatImage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class BreedSharedViewModel @Inject constructor(private val repository: CatsRepository) :
    ViewModel() {

    var breedItem by mutableStateOf<BreedItem?>(null)

    val breeds: MutableState<NetworkResult<GetBreeds>> =
        mutableStateOf(NetworkResult.Loading())

    val imageByID: MutableState<NetworkResult<CatImage>> =
        mutableStateOf(NetworkResult.Loading())
    init {

        getBreeds()
    }

    fun addBreedItem(breedImage: BreedItem) {
        breedItem = breedImage

        // Once breedItem has a value, get the cat image
        breedItem?.let {
            it.reference_image_id?.let { it1 -> getCatImageByID(it1) }
        }
    }

    private fun getBreeds() = viewModelScope.launch {

        repository.getBreeds().collect { values ->
            breeds.value = values
        }
    }

    fun getCatImageByID(referenceImageId: String) = viewModelScope.launch {

        repository.getCatImageByID(referenceImageId).collect { values ->
            imageByID.value = values
            Log.i("dem", values.data?.url + "k")
        }
    }
}
