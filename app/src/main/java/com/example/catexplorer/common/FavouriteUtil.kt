package com.example.catexplorer.common

import android.util.Log
import com.example.catexplorer.screens.favourite.model.GetFavourite
import com.example.catexplorer.screens.favourite.viewmodel.FavouriteSharedViewModel
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
import com.example.catexplorer.screens.wallpapers.viewmodel.WallpapersSharedViewModel

object FavouriteUtil {

    fun <T> postFavourite(
        postFavouriteModel: PostFavourite,
        tag: String,
        viewModel: T
    ) {
        when (viewModel) {
            is WallpapersSharedViewModel -> {
                viewModel.postFavourite(postFavouriteModel)
                Log.i(tag, "added to favourite")
            }
            is FavouriteSharedViewModel -> {
                viewModel.postFavourite(postFavouriteModel)
                Log.i(tag, "added to favourite")
            }
        }
    }

    fun <T> deleteFavourite(
        favourite: GetFavourite?,
        tag: String,
        viewModel: T,
    ) {
        when (viewModel) {
            is WallpapersSharedViewModel -> {
                if (favourite != null && !favourite.isEmpty()) {
                    val favouriteId = favourite[0].id
                    viewModel.deleteFavourite(favouriteId)
                    Log.i(tag, "deleted${favouriteId}")
                }
            }

            is FavouriteSharedViewModel -> {
                if (favourite != null && !favourite.isEmpty()) {
                    val favouriteId = favourite[0].id
                    viewModel.deleteFavourite(favouriteId)
                    Log.i(tag, "deleted${favouriteId}")
                }
            }
        }
    }

}