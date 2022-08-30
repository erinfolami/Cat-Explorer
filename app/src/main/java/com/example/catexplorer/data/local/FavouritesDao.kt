package com.example.catexplorer.data.local

import androidx.room.*

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM Favourites")
    suspend fun getAllFavourite(): List<FavouriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favouriteEntity: FavouriteEntity)

    @Delete
    suspend fun deleteFavourite(favouriteEntity: FavouriteEntity)
}