package com.example.catexplorer.common

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.catexplorer.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

fun shareImage(imageUrl: String, scope: CoroutineScope, context: Context) {

    scope.launch {
        withContext(Dispatchers.IO) {


            val cachePath = File(context.cacheDir, "images")
            cachePath.mkdirs() // don't forget to make the directory
            val stream =
                FileOutputStream("$cachePath/image.png") // overwrites this image every time
            BitmapUtil.getBitmap("TAG", context, imageUrl)
                ?.compress(Bitmap.CompressFormat.PNG, 100, stream)
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