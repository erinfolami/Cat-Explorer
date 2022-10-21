package com.example.catexplorer.screens.favourite.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catexplorer.base.NetworkResult
import com.example.catexplorer.repositories.CatsRepository
import com.example.catexplorer.screens.favourite.model.GetFavourite
import com.example.catexplorer.screens.favourite.model.GetFavouriteItem
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteSharedViewModel @Inject constructor(private val repository: CatsRepository) :
    ViewModel() {


    val allFavourite: MutableState<NetworkResult<GetFavourite>> =
        mutableStateOf(NetworkResult.Loading())

    var favouriteImageItem  by mutableStateOf<GetFavouriteItem?>(null)

    val response: MutableState<NetworkResult<GetFavourite>> =
        mutableStateOf(NetworkResult.Loading())


    fun addImageItem(image: GetFavouriteItem){
        favouriteImageItem = image
    }



    fun postFavourite(postBody: PostFavourite){
        viewModelScope.launch {
            repository.postFavourite(postBody)
        }
    }

    fun deleteFavourite(favourite_id: Int){
        viewModelScope.launch {
            repository.deleteFavourite(favourite_id)
        }
    }



    fun getFavourite(userId: String, imageId: String) = viewModelScope.launch {
        val filter = HashMap<String,String>()
        filter["sub_id"] = userId
        filter["image_id"] = imageId

        repository.getFavourite(filter).collect { values ->
            response.value = values
        }
    }

    fun getAllFavourite(userId: String) {
        viewModelScope.launch {
           val filter = HashMap<String,String>()
            filter["sub_id"] = userId
            repository.getAllFavourite(filter).collect { values ->
                Log.i("tag","item changed")
               allFavourite.value = values
            }
        }
    }
}