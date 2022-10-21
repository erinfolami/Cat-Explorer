package com.example.catexplorer.repositories

import android.util.Log
import com.example.catexplorer.base.BaseApiResponse
import com.example.catexplorer.base.NetworkResult
import com.example.catexplorer.data.remote.CatsRemoteDataSource
import com.example.catexplorer.screens.fact.model.CatFactModel
import com.example.catexplorer.screens.favourite.model.GetFavourite
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
import com.example.catexplorer.screens.wallpapers.model.CatImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class CatsRepository @Inject constructor(
    private val catsRemoteDataSource: CatsRemoteDataSource,
) : BaseApiResponse() {

    private val tag = "CatsRepository"

    suspend fun getFact(): Flow<NetworkResult<CatFactModel>> {
        return flow {
            emit(safeApiCall { catsRemoteDataSource.getCat() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getImages(filter: HashMap<String, Int>): Response<List<CatImage>> {
        return catsRemoteDataSource.getCatImages(filter)
    }

    suspend fun postFavourite(postBody: PostFavourite) {
        catsRemoteDataSource.postFavourite(postBody)
    }

    suspend fun deleteFavourite(favourite_id: Int) {
        //handling 404 error in case of subsequent Delete requests
        try {
            catsRemoteDataSource.deleteFavourite(favourite_id)
        } catch (e: HttpException) {
            Log.i(tag, "cannot find item to delete in the Api}")
        }
    }

    suspend fun getFavourite(filter: HashMap<String, String>): Flow<NetworkResult<GetFavourite>> {
        return flow {
            emit(safeApiCall { catsRemoteDataSource.getFavourite(filter) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllFavourite(filter: HashMap<String, String>): Flow<NetworkResult<GetFavourite>> {
        return flow {
            emit(safeApiCall { catsRemoteDataSource.getAllFavourites(filter) })
        }.flowOn(Dispatchers.IO)
    }

}