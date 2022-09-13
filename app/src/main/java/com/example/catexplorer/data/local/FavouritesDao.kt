package com.example.catexplorer.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM Favourites")
    fun getAllFavourite(): Flow<List<FavouriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favouriteEntity: FavouriteEntity)

    @Delete
    suspend fun deleteFavourite(favouriteEntity: FavouriteEntity)
}