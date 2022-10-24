package com.example.catexplorer

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.catexplorer.main.viewmodel.MainViewModel
import com.example.catexplorer.navigation.BottomNavScreen
import com.example.catexplorer.navigation.DetailsNavScreen
import com.example.catexplorer.screens.WallpapersScreen
import com.example.catexplorer.screens.fact.viewmodel.FactViewModel
import com.example.catexplorer.screens.favourite.FavouriteDetailScreen
import com.example.catexplorer.screens.favourite.FavouriteScreen
import com.example.catexplorer.screens.favourite.viewmodel.FavouriteSharedViewModel
import com.example.catexplorer.screens.wallpapers.WallpapersDetailScreen
import com.example.catexplorer.screens.wallpapers.viewmodel.WallpapersSharedViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {

    val mainViewModel = hiltViewModel<MainViewModel>()
    val wallpapersSharedViewModel = hiltViewModel<WallpapersSharedViewModel>()
    val favouriteSharedViewModel = hiltViewModel<FavouriteSharedViewModel>()


    Log.i("MainViewModel", "user ID ${mainViewModel.dataStoreData.value}")


    NavHost(navController, startDestination = BottomNavScreen.Fact.route) {

        composable(BottomNavScreen.Fact.route) {
            val viewModel = hiltViewModel<FactViewModel>()
            FactScreen(viewModel)
        }


        composable(BottomNavScreen.Wallpapers.route) {
            WallpapersScreen(wallpapersSharedViewModel, navController)
        }

        composable(DetailsNavScreen.WallpapersDetail.route) {
            val imageUrl = wallpapersSharedViewModel.wallpaperImageItem?.url
            Log.i("WallpapersDetail", "passed imageUrl $imageUrl")

            WallpapersDetailScreen(wallpapersSharedViewModel, mainViewModel)

        }

        composable(BottomNavScreen.Favourite.route) {
            val imageUrl = favouriteSharedViewModel.favouriteImageItem?.image?.url
            Log.i("FavouriteScreen", "passed imageUrl $imageUrl")

            FavouriteScreen(favouriteSharedViewModel, mainViewModel, navController)
        }

        composable(DetailsNavScreen.FavouritesDetail.route) {
            val imageUrl = favouriteSharedViewModel.favouriteImageItem?.image?.url
            Log.i("FavouriteDetail", "passed imageUrl $imageUrl")

            FavouriteDetailScreen(favouriteSharedViewModel, mainViewModel)

        }
    }
}