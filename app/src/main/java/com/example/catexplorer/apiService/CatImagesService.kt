package com.example.catexplorer.apiService

import com.example.catexplorer.screens.wallpapers.model.CatImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface CatImagesService {
    @GET
    suspend fun getCatImages(
        @Url url: String,
        @QueryMap filter: HashMap<String, Int>,
        @Query("api_key") api_key: String,
    ): Response<List<CatImage>>
}