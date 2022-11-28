package com.example.catexplorer.apiService

import com.example.catexplorer.screens.breed.model.GetBreeds
import com.example.catexplorer.screens.favourite.model.GetFavourite
import com.example.catexplorer.screens.wallpapers.model.CatImage
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
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
    suspend fun fetchFavourite(
        @Url url: String,
        @QueryMap filter: HashMap<String, String>,
        @Query("api_key") api_key: String,
    ): Response<GetFavourite>

    @DELETE
    suspend fun deleteFavourite(
        @Url url: String,
        @Query("api_key") api_key: String
    )

    @GET
    suspend fun getBreeds(@Url url: String): Response<GetBreeds>
}
