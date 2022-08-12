package com.example.catexplorer.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.catexplorer.MainScreenView
import com.example.catexplorer.ui.theme.CatExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            CatExplorerTheme(darkTheme = true){
                MainScreenView()
            }

        }
    }
}
