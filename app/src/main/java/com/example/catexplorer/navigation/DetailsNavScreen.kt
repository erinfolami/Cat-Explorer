package com.example.catexplorer.navigation

sealed class DetailsNavScreen(val route: String) {
    object WallpapersDetail : DetailsNavScreen("WallpapersDetail")
    object FavouritesDetail : DetailsNavScreen("FavouritesDetail")
    object BreedsDetail : DetailsNavScreen("BreedsDetail")
}
