package com.appsfactory.testtask.ui.album

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.appsfactory.testtask.data.repository.AlbumsRepository
import com.appsfactory.testtask.domain.model.Album

class FavoriteAlbumsDataSource(
    private val albumsRepository: AlbumsRepository
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
            val response = albumsRepository.getFavoriteAlbums(page, 10)

            LoadResult.Page(
                data = response.albums,
                prevKey = null,
                nextKey = if (response.albums.isNotEmpty()) response.page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}