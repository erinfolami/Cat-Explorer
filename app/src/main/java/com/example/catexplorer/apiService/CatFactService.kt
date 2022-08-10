package com.example.catexplorer.apiService

import com.example.catexplorer.screens.fact.model.CatFactModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CatFactService {
    @GET
    suspend fun getCatFact(@Url url: String): Response<CatFactModel>
}