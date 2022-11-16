package com.example.catexplorer.screens.wallpapers

import android.app.WallpaperManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.catexplorer.R
import com.example.catexplorer.common.BitmapUtil.getBitmapFromUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun WallpaperCustomDialog(
    tag: String,
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
                            setHomeWallpaper(
                                tag,
                                context,
                                imageUrl,
                                wallpaperManager,
                                coroutineScope
                            )
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
                            setLockWallpaper(
                                tag,
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
                                tag,
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




fun setHomeWallpaper(
    tag: String,
    context: Context,
    imageUrl: String,
    wallpaperManager: WallpaperManager,
    scope: CoroutineScope
) {

    scope.launch {
        withContext(Dispatchers.IO) {
            var result = wallpaperManager.setBitmap(
                getBitmapFromUrl(tag, context, imageUrl),
                null,
                false,
                WallpaperManager.FLAG_SYSTEM
            );
            if (result != 0) {
                Log.i(tag, "HomeWallpaper Set Successfully")

                //Using Handler to show Toast from a non UI Thread as Toast.makeText() and
                // other functions dealing with UI needs to be called within the main thread
                Handler(Looper.getMainLooper()).post {
                    val toast = Toast.makeText(
                        context,
                        "Wallpaper applied to Home screen.",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        }
    }

}


fun setLockWallpaper(
    tag: String,
    context: Context,
    imageUrl: String,
    wallpaperManager: WallpaperManager,
    scope: CoroutineScope
) {
    scope.launch {
        withContext(Dispatchers.IO) {
            var result = wallpaperManager.setBitmap(
                getBitmapFromUrl(tag, context, imageUrl),
                null,
                false,
                WallpaperManager.FLAG_LOCK
            );
            if (result != 0) {
                Log.i(tag, "LockWallpaper Set Successfully")

                //Using Handler to show Toast from a non UI Thread
                Handler(Looper.getMainLooper()).post {
                    val toast = Toast.makeText(
                        context,
                        "Wallpaper applied to Lock screen.",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        }
    }
}


fun setHomeAndLockWallpaper(
    tag: String,
    context: Context,
    imageUrl: String,
    wallpaperManager: WallpaperManager,
    scope: CoroutineScope
) {
    scope.launch {
        withContext(Dispatchers.IO) {
            var result = wallpaperManager.setBitmap(
                getBitmapFromUrl(tag, context, imageUrl),
                null,
                false,
                WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK
            );
            if (result != 0) {
                Log.i(tag, "HomeAndWallpaper Set Successfully")

                //Using Handler to show Toast from a non UI Thread
                Handler(Looper.getMainLooper()).post {
                    val toast = Toast.makeText(
                        context,
                        "Wallpaper applied to Lock and Home screens.",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        }
    }
}


