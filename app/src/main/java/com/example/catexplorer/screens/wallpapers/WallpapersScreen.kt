package com.example.catexplorer.screens


import ShimmerAnimation
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.catexplorer.R
import com.example.catexplorer.screens.fact.viewmodel.FactViewModel
import com.example.catexplorer.screens.wallpapers.model.CatImage
import com.example.catexplorer.screens.wallpapers.viewmodel.WallpapersViewModel


@Composable
fun WallpapersScreen(wallpapersViewModel: WallpapersViewModel) {
//    val images = wallpapersViewModel.getCatImagePagination().collectAsLazyPagingItems()
        val images = wallpapersViewModel.items.collectAsLazyPagingItems()
    WallpapersList(modifier = Modifier, images = images)
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallpapersList(modifier: Modifier, images: LazyPagingItems<CatImage>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier.background(color = Color.DarkGray)
    ) {
        items(images) { image ->
            image?.let {
                ImageCard(modifier = Modifier, image = it)
            }
        }
    }
}


@Composable
fun ImageCard(modifier: Modifier, image: CatImage) {

    Card(
        modifier = modifier
            .padding(3.dp)
            .size(250.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 15.dp
    ) {
        SubcomposeAsyncImage(
            model = image.url,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            loading = {
                ShimmerAnimation()
            }
        )
    }

}


//writing this  extension function
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


//@Composable
//fun WallpaperGrid(){
//
//}