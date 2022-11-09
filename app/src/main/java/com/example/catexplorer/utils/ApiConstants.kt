package com.example.catexplorer.utils

import com.example.catexplorer.BuildConfig

object ApiConstants {


        const val CatFact_BASE_URL = "https://catfact.ninja"
        const val FACT_URL = "/fact"


        const val TheCatApi_BASE_URL = "https://api.thecatapi.com"
        const val IMAGE_URL = "/v1/images/search"
        const val FAVOURITE_URL = "/v1/favourites"
        const val BREED_URL = "/v1/breeds"

        const val api_key = BuildConfig.CAT_FACTS_API_KEY

        const val catImage_STARTING_PAGE_INDEX = 0
        const val catImage_NETWORK_PAGE_SIZE = 20


}