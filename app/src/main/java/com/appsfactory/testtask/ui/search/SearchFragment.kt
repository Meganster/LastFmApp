package com.appsfactory.testtask.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.appsfactory.testtask.ui.base.compose.BaseComposeFragment
import kotlinx.coroutines.launch

class SearchFragment : BaseComposeFragment<SearchViewModel>() {

    override val classType = SearchViewModel::class.java

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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(it)
                    ) {
                        SearchCell(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            searchValue = viewModel.searchState,
                            onSearchChanged = viewModel::onSearchChanged
                        )
                        ArtistList(
                            items = viewModel.artistsPager.collectAsLazyPagingItems(),
                            onItemClicked = viewModel::onArtistClicked,
                            onError = viewModel::onErrorHappened
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun initObservers() {
        viewModel.navigation.Handler { artistDto ->
            launch {
                navController().navigate(SearchFragmentDirections.actionShowTopAlbums(artistDto))
            }
        }
    }
}