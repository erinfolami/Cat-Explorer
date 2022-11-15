package com.example.catexplorer

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.catexplorer.screens.fact.viewmodel.FactViewModel


@Composable
fun FactScreen(factViewModel: FactViewModel) {

    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier,
                context = context,
                factViewModel = factViewModel
            )
        },
        floatingActionButtonPosition = FabPosition.End
    )

    {
        ScreenContent(factViewModel)
    }

}

@Composable
fun ScreenContent(factViewModel: FactViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val offset = Offset(5.0f, 10.0f)

        Text(
            text = "Cat Facts!!!",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Gray,
                    offset = offset,
                    blurRadius = 10f
                )
            ),
            modifier = Modifier.padding(15.dp)
        )
        factViewModel.response.value.data?.fact?.let {
            Text(
                text = "$it",
                modifier = Modifier
                    .padding(10.dp)
                    .border(0.5.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp)
            )
        }


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(98.dp)
                .padding(25.dp)
                .clip(CircleShape),
            onClick = { fetchData(factViewModel) }) {
            Text(text = "Get Cat Fact")
        }

    }
}


@Composable
fun FloatingActionButton(modifier: Modifier, context: Context, factViewModel: FactViewModel) {

    FloatingActionButton(modifier = modifier.padding(vertical = 100.dp),
        backgroundColor = MaterialTheme.colors.primary,
        onClick = { shareCatFact(context, factViewModel) })
    {
        Icon(Icons.Outlined.Share, contentDescription = "Share Fact")
    }

}


private fun fetchData(factViewModel: FactViewModel) {
    factViewModel.fetchCatResponse()
}

fun shareCatFact(context: Context, factViewModel: FactViewModel) {
    val text = factViewModel.response.value.data?.fact

    if (text != null) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        startActivity(context, shareIntent, null)
    } else {
        val toast = Toast.makeText(context, "No Data to share", Toast.LENGTH_SHORT)
        toast.show()
    }
}

//@Preview
//@Composable
//fun PreviewMessageCard() {
//    val viewModel = hiltViewModel<MainViewModel>()
//    FactScreen(viewModel)
//}
