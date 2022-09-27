package com.example.catexplorer

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.catexplorer.navigation.BottomNavScreen
import com.example.catexplorer.navigation.DetailsNavScreen
import com.example.catexplorer.screens.FavouriteScreen
import com.example.catexplorer.screens.WallpapersScreen
import com.example.catexplorer.screens.fact.viewmodel.FactViewModel
import com.example.catexplorer.screens.favourite.FavouriteViewModel
import com.example.catexplorer.screens.wallpapers.WallpapersDetailScreen
import com.example.catexplorer.screens.wallpapers.viewmodel.WallpapersSharedViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {

    val wallpapersSharedViewModel = hiltViewModel<WallpapersSharedViewModel>()

    NavHost(navController, startDestination = BottomNavScreen.Fact.route) {

        composable(BottomNavScreen.Fact.route) {
            val viewModel = hiltViewModel<FactViewModel>()
            FactScreen(viewModel)
        }


        composable(BottomNavScreen.Wallpapers.route) {
            WallpapersScreen(wallpapersSharedViewModel, navController)
        }

        composable(DetailsNavScreen.WallpapersDetail.route) {
            val imageUrl = wallpapersSharedViewModel.imageItem?.url

            Log.i("WallpapersDetail", "passed imageUrl $imageUrl")
            WallpapersDetailScreen(wallpapersSharedViewModel)

        }

        composable(BottomNavScreen.Favourite.route) {
            val viewModel = hiltViewModel<FavouriteViewModel>()
            FavouriteScreen(viewModel, navController)
        }
    }
}