package com.example.catexplorer.screens.wallpapers.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.catexplorer.data.local.FavouriteEntity
import com.example.catexplorer.repositories.CatsRepository
import com.example.catexplorer.screens.wallpapers.paging.CatImagesSource
import com.example.catexplorer.utils.Constants.Companion.catImage_NETWORK_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel @Inject constructor(private val repository: CatsRepository) :
    ViewModel() {

    val items = Pager(PagingConfig(pageSize = catImage_NETWORK_PAGE_SIZE)) {
        CatImagesSource(repository)
    }.flow.cachedIn(viewModelScope)


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
}