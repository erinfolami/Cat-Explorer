package com.example.catexplorer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavouriteEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao
}