package com.appsfactory.testtask.ui.album

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.appsfactory.testtask.data.repository.AlbumsRepository
import com.appsfactory.testtask.ui.base.compose.BaseComposeViewModel
import javax.inject.Inject

class FavoriteAlbumsViewModel @Inject constructor(
    private val albumsRepository: AlbumsRepository
) : BaseComposeViewModel() {

    val favoriteAlbumsPager = Pager(
        PagingConfig(pageSize = 10)
    ) {
        FavoriteAlbumsDataSource(albumsRepository)
    }
        .flow
        .cachedIn(viewModelScope)
}