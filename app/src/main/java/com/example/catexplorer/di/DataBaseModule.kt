package com.example.catexplorer.di

import android.content.Context
import androidx.room.Room
import com.example.catexplorer.data.local.AppDatabase
import com.example.catexplorer.data.local.FavouritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext applicationContext: Context) : AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "Cat Database")
            .build()
    }

    @Singleton
    @Provides
    fun provideFavouritesDoa(db: AppDatabase): FavouritesDao{
        return db.favouritesDao()
    }

}