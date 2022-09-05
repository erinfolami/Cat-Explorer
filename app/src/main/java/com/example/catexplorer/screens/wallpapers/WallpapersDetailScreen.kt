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

@Composable
private fun WallpaperCustomDialog(
    imageUrl: String,
    context: Context,
    setShowDialog: (Boolean) -> Unit
) {

    val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(context)

    val coroutineScope = rememberCoroutineScope()

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(shape = RoundedCornerShape(16.dp), color = Color.DarkGray) {

            Column(modifier = Modifier.padding(20.dp)) {

                Text(text = "Set Wallpaper", style = TextStyle(fontWeight = FontWeight.ExtraBold))

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp)
                        .clickable(onClick = {
                            setHomeWallpaper(context, imageUrl, wallpaperManager, coroutineScope)
                            setShowDialog(false)
                        }),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_set_as_home),
                        tint = colorResource(
                            id = R.color.paleBlue
                        ),
                        contentDescription = "Set Wallpaper As Home Screen",
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                    Text(
                        text = "Set on Home Screen",
                        Modifier
                            .padding(start = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp)
                        .clickable(onClick = {
                            setLockWallpaper(context, imageUrl, wallpaperManager, coroutineScope)
                            setShowDialog(false)
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_set_as_lock),
                        tint = colorResource(
                            id = R.color.paleBlue
                        ),
                        contentDescription = "Set Wallpaper As Lock Screen",
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )

                    Text(text = "Set on Lock Screen", Modifier.padding(start = 15.dp))

                }

                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp)
                        .clickable(onClick = {
                            setHomeAndLockWallpaper(
                                context,
                                imageUrl,
                                wallpaperManager,
                                coroutineScope
                            )
                            setShowDialog(false)
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_set_as_home_and_lock),
                        contentDescription = "Set Wallpaper As Home and Lock Screen",
                        tint = colorResource(
                            id = R.color.paleBlue
                        ),
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )

                    Text(text = "Set on Lock and Home Screens", Modifier.padding(start = 15.dp))

                }

            }
        }
    }
}


suspend fun getBitmap(context: Context, imageUrl: String): Bitmap? {

    var bitmap: Bitmap? = null

    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .target(
            onStart = {
                Log.d(TAG, "Coil loader started.")
            },
            onSuccess = { result ->
                Log.e(TAG, "Coil loader success.")
                bitmap = result.toBitmap()
            },
            onError = {
                Log.e(TAG, "Coil loading error")
            }
        )
        .build()
    context.imageLoader.execute(imageRequest)

    return bitmap
}

fun setHomeWallpaper(
    context: Context,
    imageUrl: String,
    wallpaperManager: WallpaperManager,
    scope: CoroutineScope
) {

    scope.launch {
        withContext(Dispatchers.IO) {
            var result = wallpaperManager.setBitmap(
                getBitmap(context, imageUrl),
                null,
                false,
                WallpaperManager.FLAG_SYSTEM
            );
            if (result != 0) {
                Log.i(TAG, "HomeWallpaper Set Successfully")
            }
        }
    }

}

fun setLockWallpaper(
    context: Context,
    imageUrl: String,
    wallpaperManager: WallpaperManager,
    scope: CoroutineScope
) {
    scope.launch {
        withContext(Dispatchers.IO) {
            var result = wallpaperManager.setBitmap(
                getBitmap(context, imageUrl),
                null,
                false,
                WallpaperManager.FLAG_LOCK
            );
            if (result != 0) {
                Log.i(TAG, "LockWallpaper Set Successfully")
            }
        }
    }
}

fun setHomeAndLockWallpaper(
    context: Context,
    imageUrl: String,
    wallpaperManager: WallpaperManager,
    scope: CoroutineScope
) {
    scope.launch {
        withContext(Dispatchers.IO) {
            var result = wallpaperManager.setBitmap(
                getBitmap(context, imageUrl),
                null,
                false,
                WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK
            );
            if (result != 0) {
                Log.i(TAG, "HomeAndWallpaper Set Successfully")
            }
        }
    }
}


