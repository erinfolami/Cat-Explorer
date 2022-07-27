package com.example.catexplorer

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.catexplorer.main.MainActivity
import com.example.catexplorer.screens.fact.viewmodel.FactViewModel


@Composable
fun FactScreen(factViewModel: FactViewModel) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        val context = LocalContext.current

        Text(text = "Cat Facts!!!", fontWeight = FontWeight.Bold)

        factViewModel.response.value.data?.let {
            Text(
                text = it.fact,
                Modifier
                    .border(0.5.dp, Color.Gray)
                    .padding(4.dp)
            )
        }

        Button(onClick = { fetchData(factViewModel) }) {
            Text(text = "Get Cat Fact")
        }

        FloatingActionButton(
            modifier = Modifier.padding(160.dp),
            onClick = { shareCatFact(context, factViewModel) })
        {
            Icon(Icons.Outlined.Share, contentDescription = "Share Fact")
        }

    }
}


fun fetchData(factViewModel: FactViewModel) {
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
