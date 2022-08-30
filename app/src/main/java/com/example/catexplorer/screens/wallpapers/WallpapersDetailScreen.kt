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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import com.example.catexplorer.R
import com.example.catexplorer.data.local.FavouriteEntity
import com.example.catexplorer.screens.wallpapers.multifab.FabIdentifier
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabItem
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabState
import com.example.catexplorer.screens.wallpapers.multifab.MultiFloatingActionButton
import com.example.catexplorer.screens.wallpapers.viewmodel.WallpapersViewModel

@Composable
fun WallpapersDetailScreen(viewModel: WallpapersViewModel,imageUrl: String) {

    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }

    var items = listOf(
        MultiFabItem(
            identifier = FabIdentifier.FAVOURITE.name,
            icon = ImageBitmap.imageResource(id = R.drawable.heart),
            label = "favourite"
        ),

        MultiFabItem(
            identifier = FabIdentifier.SET_AS_WALLPAPER.name,
            icon = ImageBitmap.imageResource(id = R.drawable.wallpaper),
            label = "Set As Wallpaper"
        ),

        MultiFabItem(
            identifier = FabIdentifier.DOWNLOAD.name,
            icon = ImageBitmap.imageResource(id = R.drawable.download),
            label = "Download"
        ),

        MultiFabItem(
            identifier = FabIdentifier.SHARE.name,
            icon = ImageBitmap.imageResource(id = R.drawable.share),
            label = "Share"
        )
    )
    val favourite = FavouriteEntity(imageUrl = imageUrl)

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                fabIcon = Icons.Outlined.Add,
                toState = toState,
                items = items,
                stateChanged = { state -> toState = state },
                onFabItemClicked = { item ->
                    when (item.identifier) {
                        FabIdentifier.FAVOURITE.name -> viewModel.saveToFavourite(favourite)
//
//                        FabIdentifier.SET_AS_WALLPAPER.name -> TODO
//
//                        FabIdentifier.DOWNLOAD.name -> TODO
//
//                        FabIdentifier.SHARE.name -> TODO

                    }
                }
            )
        },

        floatingActionButtonPosition = FabPosition.End
    ) {
        ScreenContent(imageUrl)
    }


}

@Composable
fun ScreenContent(imageUrl: String) {
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