package com.appsfactory.testtask.ui.album

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.appsfactory.testtask.data.repository.AlbumsRepository
import com.appsfactory.testtask.ui.base.compose.BaseComposeViewModel
import com.appsfactory.testtask.utils.SimpleEffect
import javax.inject.Inject

class FavoriteAlbumsViewModel @Inject constructor(
    private val albumsRepository: AlbumsRepository
) : BaseComposeViewModel() {

    val favoriteAlbumsPager = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        FavoriteAlbumsDataSource(albumsRepository, PAGE_SIZE * 2)
    }
        .flow
        .cachedIn(viewModelScope)

    val navigation = SimpleEffect()

    fun onSearchButtonClicked() {
        navigation.invoke()
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}