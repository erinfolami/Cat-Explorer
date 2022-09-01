package com.example.catexplorer.screens.wallpapers

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.catexplorer.R
import com.example.catexplorer.data.local.FavouriteEntity
import com.example.catexplorer.screens.wallpapers.multifab.FabIdentifier
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabItem
import com.example.catexplorer.screens.wallpapers.multifab.MultiFabState
import com.example.catexplorer.screens.wallpapers.multifab.MultiFloatingActionButton
import com.example.catexplorer.screens.wallpapers.viewmodel.WallpapersViewModel

@Composable
fun WallpapersDetailScreen(viewModel: WallpapersViewModel, imageUrl: String) {

    var toState by remember { mutableStateOf(MultiFabState.COLLAPSED) }
    var showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        WallpaperCustomDialog(setShowDialog = { showDialog.value = it })
    }

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
private fun WallpaperCustomDialog(setShowDialog: (Boolean) -> Unit) {
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(shape = RoundedCornerShape(16.dp), color = Color.DarkGray) {

            Column(modifier = Modifier.padding(20.dp)) {

                Text(text = "Set Wallpaper", style = TextStyle(fontWeight = FontWeight.ExtraBold))

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp),
                    verticalAlignment = Alignment.CenterVertically
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
                    Text(text = "Set on Home Screen",Modifier.padding(start = 15.dp))
                }

                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp),
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

                    Text(text = "Set on Lock Screen",Modifier.padding(start = 15.dp))

                }

                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp),
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

                    Text(text = "Set on Lock and Home Screens",Modifier.padding(start = 15.dp))

                }

            }
        }
    }
}