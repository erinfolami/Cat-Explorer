package com.example.catexplorer.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.catexplorer.R
import com.example.catexplorer.data.local.FavouriteEntity
import com.example.catexplorer.screens.fact.viewmodel.FactViewModel
import com.example.catexplorer.screens.favourite.FavouriteViewModel
import com.example.catexplorer.screens.wallpapers.model.CatImage
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun FavouriteScreen(favouriteViewModel: FavouriteViewModel,navController: NavController) {

   val favourites = favouriteViewModel.response.value




    FavouriteList(modifier = Modifier, favourites = favourites, navController = navController)

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouriteList(modifier: Modifier,favourites: List<FavouriteEntity>,navController: NavController) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier.background(color = Color.DarkGray)
    ) {
       items(favourites){ favourite ->
           ImageCard(modifier = Modifier, favourite = favourite, navController = navController)
       }
    }
}

@Composable
private fun ImageCard(modifier: Modifier, favourite: FavouriteEntity, navController: NavController) {
    //encoding image url because we need to pass the url inside another url
    val encodedUrl = URLEncoder.encode(favourite.imageUrl, StandardCharsets.UTF_8.toString())

    Card(
        modifier = modifier
            .padding(3.dp)
            .size(250.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 15.dp
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(route = "WallpapersDetail/${encodedUrl}") },
            model = favourite.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
    }

}