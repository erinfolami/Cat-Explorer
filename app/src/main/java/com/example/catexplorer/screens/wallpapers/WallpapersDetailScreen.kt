package com.example.catexplorer.screens.wallpapers

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import com.example.catexplorer.R
import com.example.catexplorer.data.local.FavouriteEntity
import com.example.catexplorer.main.viewmodel.MainViewModel
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
import com.example.catexplorer.screens.wallpapers.multifab.FabIdentifier
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabItem
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabState
import com.example.catexplorer.screens.wallpapers.multifab.MultiFloatingActionButton
import com.example.catexplorer.screens.wallpapers.viewmodel.WallpapersSharedViewModel

@Composable
fun WallpapersDetailScreen(
    wallpapersSharedViewModel: WallpapersSharedViewModel,
    mainViewModel: MainViewModel
) {

    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }
    val showDialog = remember { mutableStateOf(false) }
    val imageUrl = wallpapersSharedViewModel.imageItem?.url
    val imageId = wallpapersSharedViewModel.imageItem?.id

    val userId = mainViewModel.dataStoreData.value


    val context = LocalContext.current
    val tag = "WallpapersDetailScreen"


    val items = listOf(
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
    val postFavourite = imageId?.let { PostFavourite(it, userId) }


    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                fabIcon = Icons.Outlined.Add,
                toState = toState,
                items = items,
                stateChanged = { state -> toState = state },
                onFabItemClicked = { item ->
                    when (item.identifier) {
                        FabIdentifier.FAVOURITE.name -> postFavourite?.let {
                            wallpapersSharedViewModel.postFavourite(
                                it
                            )
                        }
//
                        FabIdentifier.SET_AS_WALLPAPER.name -> showDialog.value = true
//
                        FabIdentifier.DOWNLOAD.name -> imageUrl?.let {
                            downloadImage(
                                tag, context,
                                it
                            )
                        }
//
//                        FabIdentifier.SHARE.name -> TODO

                    }
                }
            )
        },

        floatingActionButtonPosition = FabPosition.End
    ) {
        if (imageUrl != null) {
            ScreenContent(imageUrl)
        }

        if (showDialog.value) {
            if (imageUrl != null) {
                WallpaperCustomDialog(
                    setShowDialog = { showDialog.value = it },
                    imageUrl = imageUrl,
                    context = context,
                    tag = tag
                )
            }
        }

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




