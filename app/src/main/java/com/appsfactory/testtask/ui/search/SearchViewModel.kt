package com.appsfactory.testtask.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.appsfactory.testtask.data.repository.ArtistsRepository
import com.appsfactory.testtask.ui.album.FavoriteAlbumsViewModel
import com.appsfactory.testtask.ui.base.compose.BaseComposeViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val artistsRepository: ArtistsRepository
) : BaseComposeViewModel() {

    var searchState by mutableStateOf(
        TextFieldValue("")
    )

    val artistsPager = Pager(PagingConfig(pageSize = FavoriteAlbumsViewModel.PAGE_SIZE)) {
        ArtistsDataSource(artistsRepository)
    }
        .flow
        .cachedIn(viewModelScope)

    fun onSearchChanged(searchValue: TextFieldValue) {
        searchState = searchValue
    }
}