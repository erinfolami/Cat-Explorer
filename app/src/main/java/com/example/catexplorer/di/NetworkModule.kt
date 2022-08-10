package com.example.catexplorer.di

import com.example.catexplorer.apiService.CatFactService
import com.example.catexplorer.apiService.CatImagesService
import com.example.catexplorer.utils.Constants.Companion.CatFact_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            // Note this Base Url will be changed dynamically
            // and overwritten by the BaseUrl passed to the ApiService
            .baseUrl(CatFact_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCatFactInterface(retrofit: Retrofit): CatFactService {
        return retrofit.create(CatFactService::class.java)
    }

    @Singleton
    @Provides
    fun provideCatImagesInterface(retrofit: Retrofit): CatImagesService {
        return retrofit.create(CatImagesService::class.java)
    }

}