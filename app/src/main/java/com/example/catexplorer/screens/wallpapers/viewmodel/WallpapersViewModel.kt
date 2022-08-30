package com.example.catexplorer.screens.wallpapers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.catexplorer.repositories.CatsRepository
import com.example.catexplorer.screens.wallpapers.paging.CatImagesSource
import com.example.catexplorer.utils.Constants.Companion.catImage_NETWORK_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel @Inject constructor(private val repository: CatsRepository) : ViewModel() {

    val items = Pager(PagingConfig(pageSize = catImage_NETWORK_PAGE_SIZE)) {
            CatImagesSource(repository)
        }.flow.cachedIn(viewModelScope)

}