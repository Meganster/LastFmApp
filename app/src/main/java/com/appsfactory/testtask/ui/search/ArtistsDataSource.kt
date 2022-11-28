package com.appsfactory.testtask.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.appsfactory.testtask.data.dto.ArtistDto
import com.appsfactory.testtask.data.repository.ArtistsRepository

class ArtistsDataSource(
    private val artistsRepository: ArtistsRepository
) : PagingSource<Pair<String, Int>, ArtistDto>() {

    //    override fun getRefreshKey(state: PagingState<String, ArtistDto>): String? {
    //        return state.anchorPosition?.let { position ->
    //            val page = state.closestPageToPosition(position)
    //            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
    //        }
    //    }
    //
    //    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtistDto> {
    //        return try {
    //            val page = params.key ?: 1
    //            val response = artistsRepository.searchArtist("bones", page)
    //
    //            LoadResult.Page(
    //                data = response.results.artistMatches.artist.orEmpty(),
    //                prevKey = null,
    //                nextKey = if (response.results.artistMatches.artist.orEmpty().isNotEmpty()) page + 1 else null
    //            )
    //        } catch (e: Exception) {
    //            LoadResult.Error(e)
    //        }
    //    }

    override fun getRefreshKey(state: PagingState<Pair<String, Int>, ArtistDto>): Pair<String, Int>? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            val query = page?.prevKey?.first.orEmpty()
            val prevPage = page?.prevKey?.second?.minus(1)
            val nextPage = page?.nextKey?.second?.plus(1)

            return when {
                prevPage != null -> query to prevPage
                nextPage != null -> query to nextPage
                else -> null
            }
        }
    }

    override suspend fun load(params: LoadParams<Pair<String, Int>>): LoadResult<Pair<String, Int>, ArtistDto> {
        return try {
            val query = params.key?.first ?: "bo"
            val page = params.key?.second ?: 1
            val response = artistsRepository.searchArtist(query, page)

            LoadResult.Page(
                data = response.results.artistMatches.artist.orEmpty(),
                prevKey = null,
                nextKey = if (response.results.artistMatches.artist.orEmpty().isNotEmpty())
                    query to response.results.openSearchQuery.startPage.toInt() + 1
                else
                    null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}