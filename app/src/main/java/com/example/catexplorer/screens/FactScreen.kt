package com.example.catexplorer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun FactScreen(mainViewModel: MainViewModel) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Cat Facts!!!")

        mainViewModel.response.value.data?.let {
            Text(
                text = it.fact,
                Modifier
                    .border(0.5.dp, Color.Gray)
                    .padding(4.dp)
            )
        }

        Button(onClick = { fetchData(mainViewModel) }) {
            Text(text = "Get Cat Fact")
        }

    }
}


fun fetchData(mainViewModel: MainViewModel) {
    mainViewModel.fetchCatResponse()
}


//@Preview
//@Composable
//fun PreviewMessageCard() {
//    val viewModel = hiltViewModel<MainViewModel>()
//    FactScreen(viewModel)
//}
