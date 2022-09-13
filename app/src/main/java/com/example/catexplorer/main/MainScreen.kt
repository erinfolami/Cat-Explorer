package com.example.catexplorer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.catexplorer.navigation.BottomNavScreen
import com.example.catexplorer.navigation.DetailsNavScreen

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    var showButtonBar  by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    showButtonBar = when(navBackStackEntry?.destination?.route){
        DetailsNavScreen.WallpapersDetail.route  -> false  // on this screen bottom bar should be hidden
        else -> true
    }

    Scaffold(bottomBar = {if(showButtonBar) BottomNavigationBar(navController = navController) })
    {
        Box(modifier = Modifier.padding(it)) {
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreenView()
}