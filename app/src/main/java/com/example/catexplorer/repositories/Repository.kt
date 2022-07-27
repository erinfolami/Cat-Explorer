package com.example.catexplorer.repositories

import com.example.catexplorer.base.BaseApiResponse
import com.example.catexplorer.base.NetworkResult
import com.example.catexplorer.data.remote.RemoteDataSource
import com.example.catexplorer.screens.fact.model.CatFactModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getFact(): Flow<NetworkResult<CatFactModel>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getCat() })
        }.flowOn(Dispatchers.IO)
    }

}