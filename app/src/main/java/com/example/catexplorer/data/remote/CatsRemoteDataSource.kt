package com.example.catexplorer.data.remote

import com.example.catexplorer.apiService.CatFactService
import com.example.catexplorer.apiService.CatImagesService
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
import com.example.catexplorer.utils.ApiConstants.Companion.CatFact_BASE_URL
import com.example.catexplorer.utils.ApiConstants.Companion.FACT_URL
import com.example.catexplorer.utils.ApiConstants.Companion.FAVOURITE_URL
import com.example.catexplorer.utils.ApiConstants.Companion.IMAGE_URL
import com.example.catexplorer.utils.ApiConstants.Companion.TheCatApi_BASE_URL
import com.example.catexplorer.utils.ApiConstants.Companion.api_key
import javax.inject.Inject

class CatsRemoteDataSource @Inject constructor(
    private val catFactService: CatFactService,
    private val catImagesService: CatImagesService
) {

    suspend fun getCat() = catFactService.getCatFact(CatFact_BASE_URL + FACT_URL)

    suspend fun getCatImages(filter: HashMap<String,Int>) = catImagesService.getCatImages(TheCatApi_BASE_URL + IMAGE_URL, filter, api_key)

    suspend fun postFavourite(postBody: PostFavourite){
        catImagesService.postFavourite(TheCatApi_BASE_URL + FAVOURITE_URL,postBody,api_key)
    }

    suspend fun getFavourite(filter: HashMap<String, String>) = catImagesService.getFavourite(TheCatApi_BASE_URL + FAVOURITE_URL,filter, api_key)



}