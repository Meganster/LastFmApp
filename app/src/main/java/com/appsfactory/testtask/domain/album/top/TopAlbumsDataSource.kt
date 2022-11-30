package com.appsfactory.testtask.domain.album.top

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.appsfactory.testtask.data.repository.RemoteAlbumsRepository
import com.appsfactory.testtask.domain.model.Album
import com.appsfactory.testtask.domain.model.Artist
import retrofit2.HttpException
import java.io.IOException

class TopAlbumsDataSource(
    private val remoteAlbumsRepository: RemoteAlbumsRepository,
    private val artist: Artist
) : PagingSource<Int, Album>() {

    override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        return try {
            val page = params.key ?: 1
            val topAlbums = remoteAlbumsRepository.getTopAlbums(
                artist = artist,
                page = page,
                itemsPerPage = params.loadSize
            )

            Page(
                data = topAlbums.albums,
                prevKey = null,
                nextKey = if (topAlbums.albums.isNotEmpty()) page + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
