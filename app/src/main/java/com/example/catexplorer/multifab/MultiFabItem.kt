package com.example.catexplorer.multifab

import androidx.compose.ui.graphics.ImageBitmap

data class MultiFabItem(
    val identifier: String,
    val icon: ImageBitmap,
    val label: String
)

val items = listOf<MultiFabItem>()
