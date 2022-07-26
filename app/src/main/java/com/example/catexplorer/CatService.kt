package com.example.catexplorer

import com.example.catexplorer.models.CatFactModel
import retrofit2.Response
import retrofit2.http.GET

interface CatService {

    @GET(Constants.FACT_URL)
    suspend fun getCatFact(): Response<CatFactModel>
}