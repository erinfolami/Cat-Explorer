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
import com.example.catexplorer.screens.wallpapers.model.CatImage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class BreedSharedViewModel @Inject constructor(private val repository: CatsRepository) :
    ViewModel() {

    var breedItem by mutableStateOf<BreedItem?>(null)

    val breedDetailImageById: MutableState<NetworkResult<CatImage>> =
        mutableStateOf(NetworkResult.Loading())

    var uiState by mutableStateOf(BreedSharedUiState())
        private set
//
//    private val _state = MutableStateFlow(BreedSharedUiState())
//    var state = _state.stateIn(viewModelScope, SharingStarted.Eagerly, _state.value)

    init {
        getBreeds()
    }

    fun addBreedItem(breedImage: BreedItem) {
        breedItem = breedImage

        // Once breedItem has a value, get the cat image
        breedItem?.let {
            getBreedDetailImageById(it.reference_image_id)
        }
    }

    private fun getBreeds() {

        viewModelScope.launch(Dispatchers.IO) {
            val list = mutableListOf<BreedDetails>()
//            withContext(Dispatchers.Main) {
//                _state.update { _state.value.copy(breedList = list, isLoading = true) }
//            }
//            uiState = uiState.copy(isLoading = true)
            withContext(Dispatchers.Main) {
                uiState = uiState.copy(isLoading = true)
            }
            repository.getBreeds().collectLatest {
                val data = it.data
                data?.forEach { breed ->
                    val image = repository.getCatImageByID(breed.reference_image_id)
                    image.first().data?.let { breedImage ->
                        BreedDetails(breed = breed, catImage = breedImage)
                    }
                        ?.let { it -> list.add(it) }
                }
            }
//             Set isLoading back to false after data is fetched
            withContext(Dispatchers.Main) {
                uiState = uiState.copy(breedList = list, isLoading = false)
            }
// //            uiState = uiState.copy(breedList = list, isLoading = false)
//            withContext(Dispatchers.Main) {
//                _state.update { _state.value.copy(breedList = list, isLoading = false) }
//            }
        }
    }

    private fun getBreedDetailImageById(referenceImageId: String) = viewModelScope.launch {

        repository.getCatImageByID(referenceImageId).collect { values ->
            breedDetailImageById.value = values
        }
    }

    data class BreedSharedUiState(
        val breedList: List<BreedDetails> = emptyList(),
        val isLoading: Boolean = false
    )

    data class BreedDetails(val breed: BreedItem, val catImage: CatImage)
}
