package com.example.catexplorer.loading.shimmers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun WallpaperShimmerItem(brush: Brush) {
    // Column composable containing spacer shaped like a square with curved edges,
    // set the [background]'s [brush] with the brush receiving from [ShimmerAnimation]
    // Composable which is the Animation you are gonna create.

    Column {
        Spacer(
            modifier = Modifier
                .size(250.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush = brush)
        )
    }
}
