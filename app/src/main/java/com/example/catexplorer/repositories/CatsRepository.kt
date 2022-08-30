package com.example.catexplorer.repositories

import com.example.catexplorer.base.BaseApiResponse
import com.example.catexplorer.base.NetworkResult
import com.example.catexplorer.data.local.FavouriteEntity
import com.example.catexplorer.data.local.FavouritesDao
import com.example.catexplorer.data.remote.CatsRemoteDataSource
import com.example.catexplorer.screens.fact.model.CatFactModel
import com.example.catexplorer.screens.wallpapers.model.CatImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class CatsRepository @Inject constructor(
    private val catsRemoteDataSource: CatsRemoteDataSource,
    private val favouritesDao: FavouritesDao
) : BaseApiResponse() {
    suspend fun getFact(): Flow<NetworkResult<CatFactModel>> {
        return flow {
            emit(safeApiCall { catsRemoteDataSource.getCat() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getImages(filter: HashMap<String, Int>): Response<List<CatImage>> {
        return catsRemoteDataSource.getCatImages(filter)
    }

    suspend fun getAllFavourite(){
        favouritesDao.getAllFavourite()
    }

    suspend fun insertFavourite(favouriteEntity: FavouriteEntity){
        favouritesDao.insertFavourite(favouriteEntity)
    }

    suspend fun deleteFavourite(favouriteEntity: FavouriteEntity){
        favouritesDao.deleteFavourite(favouriteEntity)
    }

}