package com.example.catexplorer.screens.wallpapers

import androidx.compose.foundation.layout.*
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Share
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabState
import com.example.catexplorer.screens.wallpapers.multifab.MultiFloatingActionButton

@Composable
fun WallpapersDetailScreen(imageUrl: String) {

    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }


    Scaffold(floatingActionButton = {
        MultiFloatingActionButton(fabIcon = Icons.Outlined.Add, toState = toState) { state ->
            toState = state
        }
    },
        floatingActionButtonPosition = FabPosition.End) {
        ScreenContent(imageUrl)
    }


}

@Composable
fun ScreenContent(imageUrl: String){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
    }
}