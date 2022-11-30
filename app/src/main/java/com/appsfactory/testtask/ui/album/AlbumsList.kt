package com.appsfactory.testtask.ui.album

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.appsfactory.testtask.R
import com.appsfactory.testtask.domain.model.Album
import com.appsfactory.testtask.ui.common.ErrorItem
import com.appsfactory.testtask.ui.common.LoadingItem

@Composable
fun AlbumsList(items: LazyPagingItems<Album>) {
    LazyColumn {
        items(items) { item ->
            item?.let { AlbumCard(album = it) }
        }

        when (items.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.Error -> {
                item {
                    ErrorItem(stringResource(R.string.something_went_wrong))
                }
            }
        }

        when (items.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            is LoadState.Error -> {
                // TODO show error
            }
        }
    }
}

@Composable
fun AlbumCard(album: Album) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = album.name,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun AlbumPreview() {
    AlbumCard(album = Album("Belal", "1", "Khan", "null", "Mr."))
}