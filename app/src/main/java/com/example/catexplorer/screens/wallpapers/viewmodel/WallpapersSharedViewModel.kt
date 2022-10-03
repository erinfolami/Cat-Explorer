package com.example.catexplorer.screens.wallpapers.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.catexplorer.data.local.FavouriteEntity
import com.example.catexplorer.repositories.CatsRepository
import com.example.catexplorer.screens.wallpapers.model.CatImage
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
import com.example.catexplorer.screens.wallpapers.paging.CatImagesSource
import com.example.catexplorer.utils.ApiConstants.Companion.catImage_NETWORK_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpapersSharedViewModel @Inject constructor(private val repository: CatsRepository) :
    ViewModel() {

    val items = Pager(PagingConfig(pageSize = catImage_NETWORK_PAGE_SIZE)) {
        CatImagesSource(repository)
    }.flow.cachedIn(viewModelScope)

    var imageItem  by mutableStateOf<CatImage?>(null)

    fun addImageItem(image: CatImage){
        imageItem = image
    }

    fun saveToFavourite(favouriteEntity: FavouriteEntity) {
        viewModelScope.launch {
            repository.insertFavourite(favouriteEntity)
            Log.i("tag","added to favourite")
        }
    }

    fun getAllFavourite(){
        viewModelScope.launch {
            repository.getAllFavourite()
        }
    }

    fun postFavourite(postBody: PostFavourite){
        viewModelScope.launch {
            repository.postFavourite(postBody)
        }
    }
}