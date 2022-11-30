package com.appsfactory.testtask.domain.artist

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.appsfactory.testtask.data.repository.ArtistsRepository
import com.appsfactory.testtask.domain.model.Artist
import retrofit2.HttpException
import java.io.IOException

class ArtistsDataSource(
    private val artistsRepository: ArtistsRepository,
    private val artistName: String
) : PagingSource<Int, Artist>() {

    override fun getRefreshKey(state: PagingState<Int, Artist>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        return try {
            val page = params.key ?: 1
            val searchArtists = artistsRepository.searchArtist(
                artist = artistName,
                page = page
            )

            Page(
                data = searchArtists.artists,
                prevKey = null,
                nextKey = if (searchArtists.artists.isNotEmpty()) page + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
