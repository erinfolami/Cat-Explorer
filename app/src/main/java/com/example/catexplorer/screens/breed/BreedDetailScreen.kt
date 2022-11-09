package com.example.catexplorer.screens.breed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.catexplorer.screens.breed.model.BreedItem
import com.example.catexplorer.screens.breed.viewmodel.BreedSharedViewModel

@Composable
fun BreedDetailScreen(breedSharedViewModel: BreedSharedViewModel) {
    val breedItem = breedSharedViewModel.breedItem

    if (breedItem != null) {
        ScreenContent(breedItem)
    }
}

@Composable
fun ScreenContent(breedItem: BreedItem) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(start = 3.dp, end = 3.dp)
                .clip(RoundedCornerShape(13.dp)),
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = breedItem.image.url,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }

        Text(
            text = breedItem.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 30.dp),
            fontSize = 19.sp
        )

        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Description", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = breedItem.description, modifier = Modifier.padding(top = 5.dp))

            if (breedItem.alt_names != null && breedItem.alt_names.isNotEmpty()) {
                Text(
                    text = "Alt Names",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(text = breedItem.alt_names, modifier = Modifier.padding(top = 5.dp))
            }

            Text(
                text = "Life Span",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = breedItem.life_span, modifier = Modifier.padding(top = 5.dp))

            Text(
                text = "Temperament",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = breedItem.temperament, modifier = Modifier.padding(top = 5.dp))

            Text(
                text = "Origin",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = breedItem.origin, modifier = Modifier.padding(top = 5.dp))

            Text(
                text = "Weight",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = breedItem.weight.metric + " Kg", modifier = Modifier.padding(top = 5.dp))


        }
    }
}