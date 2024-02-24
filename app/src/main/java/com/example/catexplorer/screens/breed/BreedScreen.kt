package com.example.catexplorer.screens.breed

import ShimmerAnimation
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.catexplorer.navigation.DetailsNavScreen
import com.example.catexplorer.screens.breed.model.BreedItem
import com.example.catexplorer.screens.breed.viewmodel.BreedSharedViewModel
import com.example.catexplorer.screens.wallpapers.model.CatImage
import com.example.catexplorer.shimmer.BreedShimmerItem
import java.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun BreedScreen(breedViewModel: BreedSharedViewModel, navController: NavController) {
    val uiState = breedViewModel.uiState
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val isLoading = false

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            breedViewModel.getBreeds()
        }
    }
    Column {
        SearchView(state = textState)

        BreedList(
            uiState.breedList,
            textState,
            breedViewModel,
            navController,
            isLoading,
        )

    }
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value -> state.value = value },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clip(CircleShape)
            .size(52.dp),
        textStyle = TextStyle(fontSize = 18.sp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(onClick = {
                    state.value =
                        TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun BreedListItem(
    breed: BreedItem,
    catImage: CatImage,
    viewModel: BreedSharedViewModel,
    navController: NavController,
    isLoading: Boolean
) {

    if (isLoading) {
        ShimmerAnimation { brush ->
            BreedShimmerItem(brush = brush)
        }
    } else {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color.Gray)
                .clickable {
                    viewModel.addBreedItem(breed)
                    navController.navigate(DetailsNavScreen.BreedsDetail.route)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                SubcomposeAsyncImage(
                    model = catImage.url,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    loading = {
                        ShimmerAnimation { brush ->
                            BreedShimmerItem(brush = brush)
                        }
                    }
                )
            }

            Text(
                text = breed.name,
                modifier = Modifier.padding(start = 15.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 17.sp
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun BreedList(
    breedList: List<BreedSharedViewModel.BreedDetails>,
    state: MutableState<TextFieldValue>,
    viewModel: BreedSharedViewModel,
    navController: NavController,
    isLoading: Boolean
) {
    var filteredBreeds: List<BreedSharedViewModel.BreedDetails>

    LazyColumn(modifier = Modifier) {
        val searchedText = state.value.text

        filteredBreeds =
            if (searchedText.isEmpty()) {
                breedList
            } else {
                val resultList = mutableListOf<BreedSharedViewModel.BreedDetails>()

                for (breed in breedList) {
                    if (breed.breed.name.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                    ) {
                        resultList.add(breed)
                    }
                }
                resultList
            }

        items(filteredBreeds) { filteredBreed ->
            BreedListItem(
                breed = filteredBreed.breed, filteredBreed.catImage,
                viewModel, navController, isLoading
            )
        }
    }
}
