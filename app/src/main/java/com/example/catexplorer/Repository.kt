package com.example.catexplorer

import com.example.catexplorer.data.remote.RemoteDataSource
import com.example.catexplorer.models.CatFactModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getCat(): Flow<NetworkResult<CatFactModel>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getCat() })
        }.flowOn(Dispatchers.IO)
    }

}