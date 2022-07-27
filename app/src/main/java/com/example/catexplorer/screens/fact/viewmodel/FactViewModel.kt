package com.example.catexplorer.screens.fact.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.catexplorer.base.NetworkResult
import com.example.catexplorer.repositories.Repository
import com.example.catexplorer.screens.fact.model.CatFactModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FactViewModel @Inject constructor
    (
    private val repository: Repository,
) : ViewModel() {

    init {
        fetchCatResponse()
    }

    val response: MutableState<NetworkResult<CatFactModel>> =
        mutableStateOf(NetworkResult.Loading())

    fun fetchCatResponse() = viewModelScope.launch {
        repository.getFact().collect { values ->
            response.value = values
        }
    }
}