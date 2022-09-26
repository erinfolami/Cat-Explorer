package com.example.catexplorer.screens.favourite

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.catexplorer.data.local.FavouriteEntity
import com.example.catexplorer.repositories.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteViewModel @Inject constructor(private val repository: CatsRepository) :
    ViewModel() {

    init {
        getAllFavourite()
    }

    val response: MutableState<List<FavouriteEntity>> = mutableStateOf(listOf())

    private fun getAllFavourite() {
        viewModelScope.launch {
            repository.getAllFavourite().collect { values ->
                Log.i("tag","item changed")
                response.value = values
            }
        }
    }
}