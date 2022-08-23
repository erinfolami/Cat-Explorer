package com.example.catexplorer.screens.wallpapers.multifab

import androidx.compose.ui.graphics.ImageBitmap

data class MultiFabItem(
    val identifier: String,
    val icon: ImageBitmap,
    val label: String
)

val items = listOf<MultiFabItem>()
