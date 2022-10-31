package com.example.catexplorer.screens.wallpapers.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.catexplorer.base.NetworkResult
import com.example.catexplorer.repositories.CatsRepository
import com.example.catexplorer.screens.favourite.model.GetFavourite
import com.example.catexplorer.screens.wallpapers.model.CatImage
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
import com.example.catexplorer.screens.wallpapers.paging.CatImagesSource
import com.example.catexplorer.utils.ApiConstants.catImage_NETWORK_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpapersSharedViewModel @Inject constructor(private val repository: CatsRepository) :
    ViewModel() {

    val items = Pager(PagingConfig(pageSize = catImage_NETWORK_PAGE_SIZE)) {
        CatImagesSource(repository)
    }.flow.cachedIn(viewModelScope)

    var wallpaperImageItem  by mutableStateOf<CatImage?>(null)

    val response: MutableState<NetworkResult<GetFavourite>> =
        mutableStateOf(NetworkResult.Loading())


    fun addImageItem(image: CatImage){
        wallpaperImageItem = image
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
}