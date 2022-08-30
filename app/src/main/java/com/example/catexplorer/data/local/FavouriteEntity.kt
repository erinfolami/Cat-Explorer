package com.example.catexplorer.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "Favourites", indices = [Index(
        value = ["image_Url"],
        unique = true
    )]
)

data class FavouriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "image_Url") val imageUrl: String?
)
