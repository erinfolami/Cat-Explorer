package com.example.catexplorer.data.remote

import com.example.catexplorer.apiService.CatFactService
import com.example.catexplorer.apiService.CatImagesService
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
import com.example.catexplorer.utils.ApiConstants.BREED_URL
import com.example.catexplorer.utils.ApiConstants.CatFact_BASE_URL
import com.example.catexplorer.utils.ApiConstants.FACT_URL
import com.example.catexplorer.utils.ApiConstants.FAVOURITE_URL
import com.example.catexplorer.utils.ApiConstants.IMAGE_BY_ID_URL
import com.example.catexplorer.utils.ApiConstants.SEARCH_IMAGE_URL
import com.example.catexplorer.utils.ApiConstants.TheCatApi_BASE_URL
import com.example.catexplorer.utils.ApiConstants.api_key
import javax.inject.Inject

class CatsRemoteDataSource @Inject constructor(
    private val catFactService: CatFactService,
    private val catImagesService: CatImagesService
) {

    suspend fun getCat() = catFactService.getCatFact(CatFact_BASE_URL + FACT_URL)

    suspend fun getCatImages(filter: HashMap<String, Int>) =
        catImagesService.getCatImages(TheCatApi_BASE_URL + SEARCH_IMAGE_URL, filter, api_key)

    suspend fun postFavourite(postBody: PostFavourite) {
        catImagesService.postFavourite(TheCatApi_BASE_URL + FAVOURITE_URL, postBody, api_key)
    }

    suspend fun getFavourite(filter: HashMap<String, String>) =
        catImagesService.fetchFavourite(TheCatApi_BASE_URL + FAVOURITE_URL, filter, api_key)

    suspend fun getAllFavourites(filter: HashMap<String, String>) =
        catImagesService.fetchFavourite(TheCatApi_BASE_URL + FAVOURITE_URL, filter, api_key)

    suspend fun deleteFavourite(favourite_id: Int) {
        catImagesService.deleteFavourite(
            "$TheCatApi_BASE_URL$FAVOURITE_URL/$favourite_id",
            api_key
        )
    }

    suspend fun getBreeds() = catImagesService.getBreeds(TheCatApi_BASE_URL + BREED_URL, api_key)

    suspend fun getCatImageByID(referenceImageId: String) = catImagesService.getCatImageByID(
        "$TheCatApi_BASE_URL$IMAGE_BY_ID_URL/$referenceImageId",
        api_key
    )
}
