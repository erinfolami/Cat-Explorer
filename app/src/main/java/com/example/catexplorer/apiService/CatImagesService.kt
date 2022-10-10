package com.example.catexplorer.apiService

import com.example.catexplorer.screens.favourite.model.GetFavourite
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
import com.example.catexplorer.screens.wallpapers.model.CatImage
import retrofit2.Response
import retrofit2.http.*

interface CatImagesService {
    @GET
    suspend fun getCatImages(
        @Url url: String,
        @QueryMap filter: HashMap<String, Int>,
        @Query("api_key") api_key: String,
    ): Response<List<CatImage>>

    @POST
    suspend fun postFavourite(
        @Url url: String,
        @Body postBody: PostFavourite,
        @Query("api_key") api_key: String,
    ): Response<PostFavourite>

    @GET
    suspend fun getFavourite(
        @Url url: String,
        @QueryMap filter: HashMap<String, String>,
        @Query("api_key") api_key: String,
    ): Response<GetFavourite>
}