package com.appsfactory.testtask.ui.album.favorite

import androidx.compose.material.SnackbarResult
import androidx.compose.material.SnackbarResult.ActionPerformed
import androidx.compose.material.SnackbarResult.Dismissed
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.appsfactory.testtask.R
import com.appsfactory.testtask.data.repository.LocalAlbumsRepository
import com.appsfactory.testtask.domain.album.favorite.FavoriteAlbumsDataSource
import com.appsfactory.testtask.domain.model.DetailsAlbum
import com.appsfactory.testtask.ui.album.favorite.NavigationState.OpenDetails
import com.appsfactory.testtask.ui.album.favorite.NavigationState.OpenSearch
import com.appsfactory.testtask.ui.base.compose.BaseComposeViewModel
import com.appsfactory.testtask.utils.Effect
import com.appsfactory.testtask.utils.StringWrapper.StringResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import timber.log.Timber
import javax.inject.Inject

sealed interface NavigationState {
    object OpenSearch : NavigationState
    class OpenDetails(val details: DetailsAlbum) : NavigationState
}

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class FavoriteAlbumsViewModel @Inject constructor(
    private val localAlbumsRepository: LocalAlbumsRepository
) : BaseComposeViewModel() {

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable, "Error when loading FavoriteAlbumsViewModel")
        showSnackbar(R.string.something_went_wrong)
    }
    private val defaultScope = viewModelScope + defaultExceptionHandler

    private val updateFavoriteAlbumsStateFlow = MutableStateFlow<Unit?>(null)

    val favoriteDetailsAlbum = updateFavoriteAlbumsStateFlow
        .flatMapLatest {
            it?.let {
                Pager(PagingConfig(DEFAULT_PAGE_SIZE)) {
                    FavoriteAlbumsDataSource(localAlbumsRepository, DEFAULT_PAGE_SIZE)
                }.flow
            } ?: emptyFlow()
        }
        .cachedIn(defaultScope)

    val navigation = Effect<NavigationState>()

    fun onSearchButtonClicked() {
        navigation.set(OpenSearch)
    }

    fun onItemClicked(details: DetailsAlbum) {
        navigation.set(OpenDetails(details))
    }

    fun onItemLongClicked(details: DetailsAlbum) {
        val description = SnackbarDescription(
            text = StringResource(R.string.top_albums_start_deleting, details.name),
            buttonTitle = StringResource(R.string.top_albums_undo_deleting),
            onSnackbarAction = { onSnackbarAction(it, details) }
        )
        showSnackbar(description)
    }

    fun refreshItems() {
        defaultScope.launch {
            updateFavoriteAlbumsStateFlow.emit(Unit)
        }
    }

    private fun onSnackbarAction(snackbarResult: SnackbarResult, details: DetailsAlbum) {
        when (snackbarResult) {
            Dismissed -> deleteDetailsAlbum(details)
            ActionPerformed -> Timber.w("Abort details deleting")
        }
    }

    private fun deleteDetailsAlbum(details: DetailsAlbum) {
        defaultScope.launch {
            localAlbumsRepository.deleteDetailsAlbum(details.name)
            refreshItems()
            showSnackbar(R.string.top_albums_delete_completed, details.name)
        }
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
    }
}