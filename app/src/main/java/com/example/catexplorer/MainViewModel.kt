package com.example.catexplorer

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.catexplorer.models.CatFactModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val repository: Repository,
) : ViewModel() {

    val response: MutableState<NetworkResult<CatFactModel>> =
        mutableStateOf(NetworkResult.Loading())

    fun fetchCatResponse() = viewModelScope.launch {
        repository.getCat().collect { values ->
            response.value = values
        }
    }
}