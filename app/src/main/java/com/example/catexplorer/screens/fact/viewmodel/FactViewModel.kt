package com.example.catexplorer.screens.fact.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.catexplorer.base.NetworkResult
import com.example.catexplorer.repositories.CatsRepository
import com.example.catexplorer.screens.fact.model.CatFactModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class FactViewModel @Inject constructor(
    private val repository: CatsRepository,
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
