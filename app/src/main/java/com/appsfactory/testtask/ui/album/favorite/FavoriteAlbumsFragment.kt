package com.appsfactory.testtask.ui.album.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.viewModels
import androidx.paging.compose.collectAsLazyPagingItems
import com.appsfactory.testtask.R
import com.appsfactory.testtask.ui.album.favorite.NavigationState.OpenDetails
import com.appsfactory.testtask.ui.album.favorite.NavigationState.OpenSearch
import com.appsfactory.testtask.ui.base.compose.BaseComposeFragment
import com.appsfactory.testtask.ui.common.ContentItemsList
import com.appsfactory.testtask.ui.common.Snackbar
import com.appsfactory.testtask.ui.common.Toolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteAlbumsFragment : BaseComposeFragment() {

    override val viewModel: FavoriteAlbumsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return ComposeView(requireContext()).apply {
            setContent {
                val snackbarHostState = remember { SnackbarHostState() }
                val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)

                initObservers()
                initSnackbarObserver(snackbarHostState)

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    scaffoldState = scaffoldState,
                    topBar = { Toolbar(stringResource(R.string.favorite_albums_title)) },
                    snackbarHost = { Snackbar(it) },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = viewModel::onSearchButtonClicked,
                            backgroundColor = colorResource(R.color.colorAccent)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                tint = Color.White,
                                contentDescription = stringResource(R.string.search_button)
                            )
                        }
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(it)
                    ) {
                        ContentItemsList(
                            modifier = Modifier
                                .fillMaxSize(),
                            items = viewModel.favoriteDetailsAlbum.collectAsLazyPagingItems(),
                            onItemClicked = viewModel::onItemClicked,
                            onItemLongClicked = viewModel::onItemLongClicked,
                            onError = viewModel::onErrorHappened
                        )
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshItems()
    }

    @Composable
    private fun initObservers() {
        viewModel.navigation.Handler { state ->
            launch {
                when (state) {
                    is OpenDetails -> navController().navigate(
                        FavoriteAlbumsFragmentDirections.actionShowAlbumDetails(state.details)
                    )
                    OpenSearch -> navController().navigate(R.id.action_show_search_artists)
                }
            }
        }
    }
}