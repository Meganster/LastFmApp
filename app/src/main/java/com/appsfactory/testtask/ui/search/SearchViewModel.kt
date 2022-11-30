package com.appsfactory.testtask.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.appsfactory.testtask.R
import com.appsfactory.testtask.data.dto.ArtistDto
import com.appsfactory.testtask.data.repository.ArtistsRepository
import com.appsfactory.testtask.ui.base.compose.BaseComposeViewModel
import com.appsfactory.testtask.utils.Effect
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val artistsRepository: ArtistsRepository
) : BaseComposeViewModel() {

    val navigation = Effect<ArtistDto>()

    var searchState by mutableStateOf(
        TextFieldValue("")
    )

    val artistsPager = Pager(PagingConfig(pageSize = 10)) {
        ArtistsDataSource(artistsRepository)
    }
        .flow
        .cachedIn(viewModelScope)

    fun onSearchChanged(searchValue: TextFieldValue) {
        searchState = searchValue
    }

    fun onArtistClicked(artistDto: ArtistDto) {
        navigation.set(artistDto)
    }

    fun onErrorHappened(error: Throwable) {
        Timber.e(error)
        showSnackbar(R.string.something_went_wrong)
    }
}