package com.example.catexplorer.navigation

import androidx.annotation.StringRes

sealed class DetailsNavScreen(val route: String){
    object WallpapersDetail : DetailsNavScreen("WallpapersDetail/{Url}")
}