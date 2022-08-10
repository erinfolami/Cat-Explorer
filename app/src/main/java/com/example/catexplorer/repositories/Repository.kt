package com.example.catexplorer.repositories

import com.example.catexplorer.base.BaseApiResponse
import com.example.catexplorer.base.NetworkResult
import com.example.catexplorer.data.remote.RemoteDataSource
import com.example.catexplorer.screens.fact.model.CatFactModel
import com.example.catexplorer.screens.wallpapers.model.CatImage
import com.example.catexplorer.utils.Constants.Companion.api_key
import com.example.catexplorer.utils.Constants.Companion.catImage_NETWORK_PAGE_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getFact(): Flow<NetworkResult<CatFactModel>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getCat() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getImages(filter: HashMap<String, Int>): Response<List<CatImage>> {
        return remoteDataSource.getCatImages(filter)
    }

}