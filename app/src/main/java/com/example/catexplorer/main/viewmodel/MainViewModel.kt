package com.example.catexplorer.main.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catexplorer.data.local.dataStore.DataStoreManager
import com.example.catexplorer.utils.DataStoreConstants.userKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
    ViewModel() {

    init {
        createUserId()
        getUserId()
    }

    val dataStoreData: MutableState<String> = mutableStateOf("null")


    private fun createUserId() {
        viewModelScope.launch {
            val userUniqueID = UUID.randomUUID().toString()
            dataStoreManager.putString(key = userKey, value = userUniqueID)
        }
    }


    private fun getUserId() {
        viewModelScope.launch {

            dataStoreManager.shrdFlow.collect { values ->
                dataStoreData.value = values
            }
        }
    }

}