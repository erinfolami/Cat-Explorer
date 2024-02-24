package com.example.catexplorer.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BreedShimmerItem(brush: Brush) {
    // Column composable containing spacer shaped like a square with curved edges,
    // set the [background]'s [brush] with the brush receiving from [ShimmerAnimation]
    // Composable which is the Animation you are gonna create.

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color.Gray),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(brush = brush)
            )
            Spacer(
                modifier = Modifier
                    .width(130.dp)
                    .height(8.dp)
                    .padding(start = 15.dp)
                    .background(brush = brush),
            )
        }
    }
}

@Preview
@Composable
fun BreedShimmerItemPreview() {
    val brush = Brush.linearGradient()
    BreedShimmerItem(brush = brush)
}
