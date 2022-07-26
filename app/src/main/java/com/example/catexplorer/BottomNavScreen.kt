package com.example.catexplorer

import androidx.annotation.StringRes

sealed class BottomNavScreen(val route: String, @StringRes val resourceID: Int, val icon: Int) {
    object Fact : BottomNavScreen("fact", R.string.fact, R.drawable.ic_fact)
    object Wallpapers : BottomNavScreen("wallpapers", R.string.wallpapers, R.drawable.ic_wallpaper)
    object Favourite : BottomNavScreen("favourite", R.string.favourite, R.drawable.ic_favorite)
}
