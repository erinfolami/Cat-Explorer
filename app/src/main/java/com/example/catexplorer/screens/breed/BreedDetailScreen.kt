package com.example.catexplorer.screens.breed

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.catexplorer.screens.breed.model.BreedItem
import com.example.catexplorer.screens.breed.viewmodel.BreedSharedViewModel
import com.example.catexplorer.screens.wallpapers.model.CatImage
import com.gowtham.ratingbar.RatingBar

@Composable
fun BreedDetailScreen(breedSharedViewModel: BreedSharedViewModel) {

    val breedItem = breedSharedViewModel.breedItem

    val catImageByIdBreedDetail = breedSharedViewModel.breedDetailImageById.value.data

    if (breedItem != null && catImageByIdBreedDetail != null) {
        ScreenContent(breedItem, catImageByIdBreedDetail)
    }
}

@Composable
fun ScreenContent(breedItem: BreedItem, catImage: CatImage) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        ImageSection(breedItem, catImage)

        Text(
            text = breedItem.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            fontSize = 19.sp,
            textAlign = TextAlign.Center
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Adaptability", fontWeight = FontWeight.Bold, fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.adaptability)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Affection level", fontWeight = FontWeight.Bold,
                    fontSize = 16.sp, modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.affection_level)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Child friendly",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.child_friendly)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Dog friendly",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.dog_friendly)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Energy level",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.energy_level)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Grooming",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.grooming)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Health issues",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.health_issues)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "intelligence",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.intelligence)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Shedding level",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.shedding_level)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Social needs",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.social_needs)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Stranger friendly",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.stranger_friendly)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Vocalisation",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.vocalisation)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Experimental",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.experimental)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Hairless",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.hairless)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Natural",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.natural)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Rare",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.rare)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Rex",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.rex)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Suppressed tail",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.suppressed_tail)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Short legs", fontWeight = FontWeight.Bold, fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                BreedRatingBar(value = breedItem.short_legs)
            }
        }
    }
}

@Composable
fun ImageSection(breedItem: BreedItem, catImage: CatImage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(15.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = catImage.url,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun BreedRatingBar(value: Int) {

    RatingBar(
        value = value.toFloat(), numStars = 5,
        onRatingChanged = {},
        isIndicator = true,
        size = 16.dp,
        modifier = Modifier
            .background(color = Color.DarkGray)
            .padding(end = 20.dp)
    )
}
