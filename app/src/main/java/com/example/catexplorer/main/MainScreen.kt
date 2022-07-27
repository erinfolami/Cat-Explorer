package com.example.catexplorer

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreenView() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigationBar(navController = navController) })
    {
        NavigationGraph(navController = navController)
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreenView()
}