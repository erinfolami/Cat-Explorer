package com.example.catexplorer.screens.favourite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.catexplorer.main.viewmodel.MainViewModel
import com.example.catexplorer.navigation.DetailsNavScreen
import com.example.catexplorer.screens.favourite.viewmodel.FavouriteSharedViewModel
import com.example.catexplorer.screens.favourite.model.GetFavourite
import com.example.catexplorer.screens.favourite.model.GetFavouriteItem


@Composable
fun FavouriteScreen(
    favouriteSharedViewModel: FavouriteSharedViewModel,
    mainViewModel: MainViewModel,
    navController: NavController
) {

    val favourites = favouriteSharedViewModel.allFavourite.value.data

    val userId = mainViewModel.dataStoreData.value


    favouriteSharedViewModel.getAllFavourite(userId)

    if (favourites != null && favourites.isEmpty()) {
            EmptyFavourite(Modifier, navController)
    }



    if (favourites != null) {
        FavouriteList(
            modifier = Modifier,
            favourites = favourites,
            navController = navController,
            viewModel = favouriteSharedViewModel
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouriteList(
    modifier: Modifier,
    favourites: GetFavourite,
    navController: NavController,
    viewModel: FavouriteSharedViewModel
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier.background(color = Color.DarkGray)
    ) {
        items(favourites) { favourite ->
            ImageCard(
                modifier = Modifier,
                image = favourite,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun ImageCard(
    modifier: Modifier,
    image: GetFavouriteItem,
    viewModel: FavouriteSharedViewModel,
    navController: NavController
) {

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
                .clickable {
                    viewModel.addImageItem(image)
                    navController.navigate(route = DetailsNavScreen.FavouritesDetail.route)
                },
            model = image.image.url,
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
    }

}
