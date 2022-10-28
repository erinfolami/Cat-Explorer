package com.example.catexplorer.screens.wallpapers

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.catexplorer.BuildConfig
import com.example.catexplorer.R
import com.example.catexplorer.main.viewmodel.MainViewModel
import com.example.catexplorer.screens.favourite.model.GetFavourite
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
import com.example.catexplorer.screens.wallpapers.multifab.FabIdentifier
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabItem
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabState
import com.example.catexplorer.screens.wallpapers.multifab.MultiFloatingActionButton
import com.example.catexplorer.screens.wallpapers.viewmodel.WallpapersSharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream


@Composable
fun WallpapersDetailScreen(
    wallpapersSharedViewModel: WallpapersSharedViewModel,
    mainViewModel: MainViewModel
) {
    val tag = "WallpapersDetailScreen"
    val context = LocalContext.current

    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }
    val showDialog = remember { mutableStateOf(false) }
    val imageUrl = wallpapersSharedViewModel.wallpaperImageItem?.url
    val imageId = wallpapersSharedViewModel.wallpaperImageItem?.id

    val favourite = wallpapersSharedViewModel.response.value.data

    val coroutineScope = rememberCoroutineScope()
    val userId = mainViewModel.dataStoreData.value


    val items = listOf(
        MultiFabItem(
            identifier = FabIdentifier.FAVOURITE.name,
            icon = ImageBitmap.imageResource(id = R.drawable.heart),
            label = "Add to favourite"
        ),

        MultiFabItem(
            identifier = FabIdentifier.DELETE_FAVOURITE.name,
            icon = ImageBitmap.imageResource(id = R.drawable.delete),
            label = "Delete favourite"
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
    val postFavouriteModel = imageId?.let { PostFavourite(it, userId) }


    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                fabIcon = Icons.Outlined.Add,
                toState = toState,
                items = items,
                stateChanged = { state -> toState = state },
                onFabItemClicked = { item ->
                    when (item.identifier) {
                        FabIdentifier.FAVOURITE.name -> postFavouriteModel?.let {
                            postFavourite(
                                it,
                                tag,
                                wallpapersSharedViewModel
                            )
                        }


                        FabIdentifier.DELETE_FAVOURITE.name -> deleteFavourite(
                            favourite, tag,
                            wallpapersSharedViewModel

                        )
                        FabIdentifier.SET_AS_WALLPAPER.name -> onShowDialog(showDialog)
//
                        FabIdentifier.DOWNLOAD.name -> imageUrl?.let {
                            downloadImage(
                                tag, context,
                                it
                            )
                        }
//
                        FabIdentifier.SHARE.name -> imageUrl?.let {
                            shareImage(
                                it,
                                coroutineScope,
                                context
                            )
                        }

                    }
                }
            )
        },

        floatingActionButtonPosition = FabPosition.End
    ) {
        if (imageUrl != null) {
            ScreenContent(imageUrl)
        }

        if (imageId != null) {
            wallpapersSharedViewModel.getFavourite(userId, imageId)

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

private fun postFavourite(
    postFavouriteModel: PostFavourite,
    tag: String,
    viewModel: WallpapersSharedViewModel
) {
    viewModel.postFavourite(postFavouriteModel)
    Log.i(tag, "added to favourite")
}

private fun deleteFavourite(
    favourite: GetFavourite?,
    tag: String,
    viewModel: WallpapersSharedViewModel
) {
    if (favourite != null && !favourite.isEmpty()) {
        val favouriteId = favourite[0].id
        viewModel.deleteFavourite(favouriteId)
        Log.i(tag, "deleted${favouriteId}")
    }
}

private fun onShowDialog(showDialog: MutableState<Boolean>) {
    showDialog.value = true
}


fun shareImage(imageUrl: String, scope: CoroutineScope, context: Context) {

    scope.launch {
        withContext(Dispatchers.IO) {


            val cachePath = File(context.cacheDir, "images")
            cachePath.mkdirs() // don't forget to make the directory
            val stream =
                FileOutputStream("$cachePath/image.png") // overwrites this image every time
            getBitmap("TAG", context, imageUrl)?.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()

            val imagePath = File(context.cacheDir, "images")
            val newFile = File(imagePath, "image.png")
            val contentUri: Uri = FileProvider.getUriForFile(
                context,
                "${BuildConfig.APPLICATION_ID}.provider",
                newFile
            )

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_STREAM, contentUri)
            context.startActivity(Intent.createChooser(intent, null))

        }
    }
}


