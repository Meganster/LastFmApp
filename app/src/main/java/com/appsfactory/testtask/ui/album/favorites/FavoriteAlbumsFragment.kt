package com.appsfactory.testtask.ui.album.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.appsfactory.testtask.R
import com.appsfactory.testtask.ui.album.AlbumsList
import com.appsfactory.testtask.ui.base.compose.BaseComposeFragment
import kotlinx.coroutines.launch

class FavoriteAlbumsFragment : BaseComposeFragment<FavoriteAlbumsViewModel>() {

    override val classType = FavoriteAlbumsViewModel::class.java

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
                    scaffoldState = scaffoldState
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(it),
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onClick = { viewModel.onSearchButtonClicked() },
                            content = {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = stringResource(R.string.search_artist_button),
                                    textAlign = TextAlign.Center
                                )
                            }
                        )
                        AlbumsList(viewModel.favoriteAlbumsPager.collectAsLazyPagingItems())
                    }
                }
            }
        }
    }

    @Composable
    private fun initObservers() {
        viewModel.navigation.Handler {
            launch {
                navController().navigate(R.id.action_show_search_artists)
            }
        }
    }
}