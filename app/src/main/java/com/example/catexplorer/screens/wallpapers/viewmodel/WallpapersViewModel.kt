package com.example.catexplorer.screens.wallpapers.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catexplorer.base.NetworkResult
import com.example.catexplorer.repositories.Repository
import com.example.catexplorer.screens.fact.model.CatFactModel
import com.example.catexplorer.screens.wallpapers.model.CatImage
import com.example.catexplorer.screens.wallpapers.paging.CatImagesSource
import com.example.catexplorer.utils.Constants.Companion.catImage_NETWORK_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val items = Pager(PagingConfig(pageSize = catImage_NETWORK_PAGE_SIZE)) {
            CatImagesSource(repository)
        }.flow.cachedIn(viewModelScope)

}