package com.example.catexplorer.common

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import java.io.File

fun downloadImage(tag: String, context: Context, imageUrl: String) {

    val directory = File(Environment.DIRECTORY_PICTURES)

    if (!directory.exists()) {
        directory.mkdir()
    }

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    val downloadUri = Uri.parse(imageUrl)

    val imageUrlSubString = imageUrl.substring(imageUrl.lastIndexOf("/") + 1)

    try {
        val request = DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI or
                    DownloadManager.Request.NETWORK_MOBILE
            )
                .setAllowedOverRoaming(false)
                .setTitle(imageUrlSubString)
                .setDescription("")
                .setDestinationInExternalPublicDir(
                    directory.toString(),
                    imageUrlSubString
                )
        }
        downloadManager.enqueue(request)

        Toast.makeText(
            context,
            "Image downloaded at $directory" + File.separator + imageUrlSubString,
            Toast.LENGTH_SHORT
        ).show()
        Log.i(tag, "Image download started.")
    } catch (e: Exception) {
        Toast.makeText(context, "Image download failed", Toast.LENGTH_SHORT).show()
        Log.i(tag, "Image download failed. Exception: $e")
    }
}
