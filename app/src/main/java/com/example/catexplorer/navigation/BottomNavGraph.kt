package com.example.catexplorer

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.catexplorer.navigation.BottomNavScreen
import com.example.catexplorer.screens.FavouriteScreen
import com.example.catexplorer.screens.WallpapersScreen
import com.example.catexplorer.screens.fact.viewmodel.FactViewModel
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
            WallpapersScreen(viewModel)
        }
        composable(BottomNavScreen.Favourite.route) {
            FavouriteScreen()
        }
    }
}