package com.example.catexplorer.screens.wallpapers.model

data class CatImage(
    val breeds: List<Breed>,
    val categories: List<Category>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)