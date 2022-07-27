package com.example.catexplorer.apiService

import com.example.catexplorer.constant.Constants
import com.example.catexplorer.screens.fact.model.CatFactModel
import retrofit2.Response
import retrofit2.http.GET

interface CatService {

    @GET(Constants.FACT_URL)
    suspend fun getCatFact(): Response<CatFactModel>
}