package com.example.catexplorer.data.remote

import com.example.catexplorer.apiService.CatService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val catService: CatService) {

    suspend fun getCat() = catService.getCatFact()

}