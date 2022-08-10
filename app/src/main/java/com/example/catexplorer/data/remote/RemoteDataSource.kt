package com.example.catexplorer.data.remote

import com.example.catexplorer.apiService.CatFactService
import com.example.catexplorer.apiService.CatImagesService
import com.example.catexplorer.utils.Constants.Companion.CatFact_BASE_URL
import com.example.catexplorer.utils.Constants.Companion.FACT_URL
import com.example.catexplorer.utils.Constants.Companion.IMAGE_URL
import com.example.catexplorer.utils.Constants.Companion.TheCatApi_BASE_URL
import com.example.catexplorer.utils.Constants.Companion.api_key
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val catFactService: CatFactService,
    private val catImagesService: CatImagesService
) {

    suspend fun getCat() = catFactService.getCatFact(CatFact_BASE_URL + FACT_URL)

    suspend fun getCatImages(filter: HashMap<String,Int>) = catImagesService.getCatImages(TheCatApi_BASE_URL + IMAGE_URL, filter, api_key)



}