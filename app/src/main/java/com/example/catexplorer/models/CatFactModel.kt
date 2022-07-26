package com.example.catexplorer.models

import com.google.gson.annotations.SerializedName

data class CatFactModel(
    @SerializedName("fact")
    val fact: String,
    @SerializedName("length")
    val length: Int
)