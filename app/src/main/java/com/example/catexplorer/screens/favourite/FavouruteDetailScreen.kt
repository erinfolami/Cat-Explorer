package com.example.catexplorer.screens.favourite

import android.util.Log
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
import com.example.catexplorer.common.shareImage
import com.example.catexplorer.main.viewmodel.MainViewModel
import com.example.catexplorer.screens.favourite.model.GetFavourite
import com.example.catexplorer.screens.favourite.viewmodel.FavouriteSharedViewModel
import com.example.catexplorer.screens.wallpapers.WallpaperCustomDialog
import com.example.catexplorer.common.downloadImage
import com.example.catexplorer.screens.wallpapers.model.PostFavourite
import com.example.catexplorer.screens.wallpapers.multifab.FabIdentifier
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabItem
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabState
import com.example.catexplorer.screens.wallpapers.multifab.MultiFloatingActionButton

@Composable
fun FavouriteDetailScreen(
    favouriteSharedViewModel: FavouriteSharedViewModel,
    mainViewModel: MainViewModel
) {
    val tag = "WallpapersDetailScreen"
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()


    val userId = mainViewModel.dataStoreData.value

    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }
    val showDialog = remember { mutableStateOf(false) }
    val imageUrl = favouriteSharedViewModel.favouriteImageItem?.image?.url
    val imageId = favouriteSharedViewModel.favouriteImageItem?.image_id

    val favourite = favouriteSharedViewModel.response.value.data

    val postFavouriteModel = imageId?.let { PostFavourite(it, userId) }



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

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                fabIcon = Icons.Outlined.Add,
                toState = toState,
                items = items,
                stateChanged = { state -> toState = state },
                onFabItemClicked = { item ->
                    when (item.identifier) {
                        FabIdentifier.FAVOURITE.name -> postFavouriteModel?.let { postFavourite(tag,postFavouriteModel, favouriteSharedViewModel) }


                        FabIdentifier.DELETE_FAVOURITE.name -> deleteFavourite(
                            favourite,
                            favouriteSharedViewModel,
                            tag
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
                        FabIdentifier.SHARE.name ->  imageUrl?.let {
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
            favouriteSharedViewModel.getFavourite(userId, imageId)

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

private fun postFavourite(tag: String, postFavouriteModel: PostFavourite, viewModel: FavouriteSharedViewModel){
    viewModel.postFavourite(postFavouriteModel)
    Log.i(tag, "added to favourite")
}

private fun deleteFavourite(
    favourite: GetFavourite?,
    viewModel: FavouriteSharedViewModel,
    tag: String
) {

    if (favourite != null && !favourite.isEmpty()) {
        val favouriteId = favourite[0].id
        viewModel.deleteFavourite(favouriteId)
        Log.i(tag, "deleted")
    }
}

private fun onShowDialog(showDialog: MutableState<Boolean>){
    showDialog.value = true
}




