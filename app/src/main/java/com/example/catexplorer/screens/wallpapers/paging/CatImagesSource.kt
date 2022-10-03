package com.example.catexplorer.screens.wallpapers.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catexplorer.repositories.CatsRepository
import com.example.catexplorer.screens.wallpapers.model.CatImage
import com.example.catexplorer.utils.ApiConstants.Companion.catImage_NETWORK_PAGE_SIZE
import com.example.catexplorer.utils.ApiConstants.Companion.catImage_STARTING_PAGE_INDEX
import javax.inject.Inject


class CatImagesSource @Inject constructor(private val catsRepository: CatsRepository) :
    PagingSource<Int, CatImage>() {

    override fun getRefreshKey(state: PagingState<Int, CatImage>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatImage> {
        return try {
            val pageNumber = params.key ?: catImage_STARTING_PAGE_INDEX

            val filter = HashMap<String, Int>()
            filter["page"] = pageNumber
            filter["limit"] = catImage_NETWORK_PAGE_SIZE

            val response = catsRepository.getImages(filter)

            val data = response.body() ?: emptyList()

            LoadResult.Page(
                data = data,
                prevKey =
                if (pageNumber == catImage_STARTING_PAGE_INDEX) null
                else pageNumber - 1,
                nextKey = if (response.body()?.isNotEmpty() == true) pageNumber.plus(1) else null

            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}