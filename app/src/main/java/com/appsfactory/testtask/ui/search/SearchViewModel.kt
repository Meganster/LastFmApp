package com.appsfactory.testtask.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.appsfactory.testtask.R.string
import com.appsfactory.testtask.domain.artist.ArtistsInteractor
import com.appsfactory.testtask.domain.model.Artist
import com.appsfactory.testtask.ui.base.compose.BaseComposeViewModel
import com.appsfactory.testtask.utils.Effect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.plus
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val artistsInteractor: ArtistsInteractor
) : BaseComposeViewModel() {

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable, "Error when loading SearchViewModel")
        showSnackbar(string.something_went_wrong)
    }
    private val defaultScope = viewModelScope + defaultExceptionHandler

    private val artistStateFlow = MutableStateFlow("")

    val navigation = Effect<Artist>()

    var searchState by mutableStateOf(
        TextFieldValue("")
    )

    val artists = artistStateFlow
        .flatMapLatest {
            artistsInteractor.searchArtist(it, DEFAULT_PAGE_SIZE)
        }
        .cachedIn(defaultScope)

    fun onSearchChanged(searchValue: TextFieldValue) {
        searchState = searchValue
    }

    fun onStartSearchClicked() {
        artistStateFlow.value = searchState.text
    }

    fun onArtistClicked(artist: Artist) {
        navigation.set(artist)
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
    }
}