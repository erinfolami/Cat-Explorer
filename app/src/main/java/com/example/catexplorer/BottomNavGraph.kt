package com.example.catexplorer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.catexplorer.screens.FavouriteScreen
import com.example.catexplorer.screens.WallpapersScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavScreen.Fact.route) {

        composable(BottomNavScreen.Fact.route) {
            val viewModel = hiltViewModel<MainViewModel>()
            FactScreen(viewModel)
        }

        composable(BottomNavScreen.Wallpapers.route) {
            WallpapersScreen()
        }
        composable(BottomNavScreen.Favourite.route) {
            FavouriteScreen()
        }
    }
}