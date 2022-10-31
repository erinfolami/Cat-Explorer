package com.example.catexplorer.common

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import coil.imageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object BitmapUtil {


    suspend fun getBitmap(tag: String, context: Context, imageUrl: String): Bitmap? {

        var bitmap: Bitmap? = null

        val imageRequest = ImageRequest.Builder(context)
            .data(imageUrl)
            .target(
                onStart = {
                    Log.d(tag, "Coil loader started.")
                },
                onSuccess = { result ->
                    Log.e(tag, "Coil loader success.")
                    bitmap = result.toBitmap()
                },
                onError = {
                    Log.e(tag, "Coil loading error")
                }
            )
            .build()
        context.imageLoader.execute(imageRequest)

        return bitmap
    }
}