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
import com.example.catexplorer.screens.FavouriteScreen
import com.example.catexplorer.screens.WallpapersScreen
import com.example.catexplorer.screens.fact.viewmodel.FactViewModel
import com.example.catexplorer.screens.wallpapers.WallpapersDetailScreen
import com.example.catexplorer.screens.wallpapers.viewmodel.WallpapersViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavScreen.Fact.route) {

        composable(BottomNavScreen.Fact.route) {
            val viewModel = hiltViewModel<FactViewModel>()
            FactScreen(viewModel)
        }


        composable(BottomNavScreen.Wallpapers.route) {
            val viewModel = hiltViewModel<WallpapersViewModel>()
            WallpapersScreen(viewModel, navController)
        }

        composable("WallpapersDetail/{Url}",
            arguments = listOf(navArgument("Url") { type = NavType.StringType })){
            val viewModel = hiltViewModel<WallpapersViewModel>()

            val imageUrl =  it.arguments?.getString("Url")
            if (imageUrl != null) {
                Log.i("WallpapersDetail","passed imageUrl $imageUrl")
                WallpapersDetailScreen(viewModel ,imageUrl = imageUrl)
            }
        }

        composable(BottomNavScreen.Favourite.route) {
            FavouriteScreen()
        }
    }
}