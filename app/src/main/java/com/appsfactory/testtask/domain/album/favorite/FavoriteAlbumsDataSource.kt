package com.appsfactory.testtask.domain.album.favorite

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.appsfactory.testtask.data.repository.LocalAlbumsRepository
import com.appsfactory.testtask.data.repository.RemoteAlbumsRepository
import com.appsfactory.testtask.domain.model.DetailsAlbum

class FavoriteAlbumsDataSource(
    private val localAlbumsRepository: LocalAlbumsRepository,
    private val itemsPerPage: Int
) : PagingSource<Int, DetailsAlbum>() {

    override fun getRefreshKey(state: PagingState<Int, DetailsAlbum>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DetailsAlbum> {
        return try {
            val page = params.key ?: 1
            val response = localAlbumsRepository.getFavoriteAlbums(page, itemsPerPage)

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}