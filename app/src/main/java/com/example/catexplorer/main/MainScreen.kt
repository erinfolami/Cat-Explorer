package com.example.catexplorer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.catexplorer.navigation.DetailsNavScreen
import com.example.catexplorer.navigation.NavigationGraph

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        // on this screens bottom bar should be hidden
        DetailsNavScreen.WallpapersDetail.route -> {
            bottomBarState.value = false
        }
        DetailsNavScreen.FavouritesDetail.route -> {
            bottomBarState.value = false
        }

        DetailsNavScreen.BreedsDetail.route -> {
            bottomBarState.value = false
        }
        else -> bottomBarState.value = true
    }

    com.google.accompanist.insets.ui.Scaffold(bottomBar = {
        BottomNavigationBar(
            navController = navController,
            bottomBarState
        )
    }) {
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
