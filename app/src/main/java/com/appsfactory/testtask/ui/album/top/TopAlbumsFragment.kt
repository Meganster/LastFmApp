package com.appsfactory.testtask.ui.album.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.appsfactory.testtask.R
import com.appsfactory.testtask.ui.base.compose.BaseComposeFragment
import com.appsfactory.testtask.ui.common.ContentItemsList
import com.appsfactory.testtask.ui.common.Snackbar
import com.appsfactory.testtask.ui.common.Toolbar
import kotlinx.coroutines.launch

class TopAlbumsFragment : BaseComposeFragment<TopAlbumsViewModel>() {

    override val classType = TopAlbumsViewModel::class.java

    private lateinit var params: TopAlbumsFragmentArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        params = TopAlbumsFragmentArgs.fromBundle(requireArguments())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return ComposeView(requireContext()).apply {
            setContent {
                val snackbarHostState = remember { SnackbarHostState() }
                val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)

                initObservers()
                initSnackbarObserver(snackbarHostState)

                viewModel.loadTopAlbums(params.artist)

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    scaffoldState = scaffoldState,
                    topBar = { Toolbar(stringResource(R.string.top_albums_title, params.artist.name)) },
                    snackbarHost = { Snackbar(it) }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(it)
                    ) {
                        ContentItemsList(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp),
                            items = viewModel.topAlbums.collectAsLazyPagingItems(),
                            onItemClicked = viewModel::onAlbumClicked,
                            onItemLongClicked = viewModel::onAlbumLongClicked,
                            onError = viewModel::onErrorHappened
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun initObservers() {
        viewModel.navigation.Handler { params ->
            launch {
                navController().navigate(TopAlbumsFragmentDirections.actionShowAlbumDetails(params))
            }
        }
    }
}