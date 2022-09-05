package com.example.catexplorer.screens.wallpapers

import android.app.WallpaperManager
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LifecycleCoroutineScope
import coil.ImageLoader
import coil.imageLoader
import coil.request.ImageRequest
import com.example.catexplorer.R
import com.example.catexplorer.data.local.FavouriteEntity
import com.example.catexplorer.screens.wallpapers.multifab.FabIdentifier
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabItem
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabState
import com.example.catexplorer.screens.wallpapers.multifab.MultiFloatingActionButton
import com.example.catexplorer.screens.wallpapers.viewmodel.WallpapersViewModel
import kotlinx.coroutines.*

@Composable
fun WallpapersDetailScreen(viewModel: WallpapersViewModel, imageUrl: String) {

    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }
    val showDialog = remember { mutableStateOf(false) }

    val context = LocalContext.current


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
                        FabIdentifier.SET_AS_WALLPAPER.name -> showDialog.value = true
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

        if (showDialog.value) {
            WallpaperCustomDialog(
                setShowDialog = { showDialog.value = it },
                imageUrl = imageUrl,
                context = context
            )
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




