package com.example.catexplorer.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.catexplorer.MainScreenView
import com.example.catexplorer.R
import com.example.catexplorer.ui.theme.CatExplorerTheme
import com.example.catexplorer.ui.theme.DarkCharcoal
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            val systemUiController = rememberSystemUiController()

            SideEffect {
                systemUiController.let {
                    it.setStatusBarColor(Color.DarkGray)
                    it.setNavigationBarColor(color = DarkCharcoal)
                }
            }

            CatExplorerTheme(darkTheme = true) {
                MainScreenView()
            }

        }
    }
}
