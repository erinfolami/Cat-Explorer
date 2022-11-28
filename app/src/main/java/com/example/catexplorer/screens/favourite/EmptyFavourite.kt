package com.example.catexplorer.screens.favourite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.catexplorer.R

@Composable
fun EmptyFavourite(modifier: Modifier, navController: NavController) {

    val scrollState = rememberScrollState()

    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(state = scrollState), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.empty_favourite),
            contentDescription = "null",
            modifier = Modifier
        )

        Text(
            text = "No Favourite Yet!",
            modifier = Modifier.padding(top = 45.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Gray,
                    blurRadius = 10f
                )
            )
        )

        Text(
            "Once you favourite a Wallpaper, you'll see them here.",
            modifier = Modifier.padding(top = 25.dp),
            fontSize = 16.sp
        )

        Text(
            text = "Add Favourite",
            modifier = Modifier
                .padding(25.dp)
                .clickable {
                    navController.navigate(route = "wallpapers") {
                        popUpTo("fact")
                    }
                },
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Yellow
        )
    }

}