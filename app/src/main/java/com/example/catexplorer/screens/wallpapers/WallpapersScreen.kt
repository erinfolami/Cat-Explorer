package com.example.catexplorer.screens

import ShimmerAnimation
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.example.catexplorer.navigation.DetailsNavScreen
import com.example.catexplorer.screens.wallpapers.model.CatImage
import com.example.catexplorer.screens.wallpapers.viewmodel.WallpapersSharedViewModel
import com.example.catexplorer.shimmer.WallpaperShimmerItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun WallpapersScreen(
    wallpapersSharedViewModel: WallpapersSharedViewModel,
    navController: NavController
) {
    val images = wallpapersSharedViewModel.items.collectAsLazyPagingItems()
    val state =
        rememberSwipeRefreshState(isRefreshing = images.loadState.refresh is LoadState.Loading)

    WallpapersScreenContent(
        state = state,
        images = images,
        navController = navController,
        wallpapersSharedViewModel
    )
}

@Composable
fun WallpapersScreenContent(
    state: SwipeRefreshState,
    images: LazyPagingItems<CatImage>,
    navController: NavController,
    wallpapersSharedViewModel: WallpapersSharedViewModel
) {
    SwipeRefresh(state = state, onRefresh = { images.refresh() }) {

        WallpapersList(
            modifier = Modifier,
            images = images,
            navController = navController,
            wallpapersSharedViewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallpapersList(
    modifier: Modifier,
    images: LazyPagingItems<CatImage>,
    navController: NavController,
    viewModel: WallpapersSharedViewModel
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier.background(color = Color.DarkGray)
    ) {
        items(images) { image ->
            image?.let {
                ImageCard(
                    modifier = Modifier,
                    image = it,
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
private fun ImageCard(
    modifier: Modifier,
    image: CatImage,
    viewModel: WallpapersSharedViewModel,
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
                    navController.navigate(route = DetailsNavScreen.WallpapersDetail.route)
                },
            model = image.url,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            loading = {
                ShimmerAnimation { brush ->
                    WallpaperShimmerItem(brush = brush)
                }
            }
        )
    }
}

// writing this  extension function
// so that LazyVerticalGrid can accept lazyPagingItems
@ExperimentalFoundationApi
public fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}
