package com.appsfactory.testtask.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.appsfactory.testtask.R

@Composable
fun <T : BaseContentItem> ContentItemsList(
    modifier: Modifier,
    items: LazyPagingItems<T>,
    onItemClicked: (T) -> Unit,
    onError: (Throwable) -> Unit,
    onItemLongClicked: (T) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items) { item ->
            item?.let {
                ContentItem(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    item = it,
                    onClick = onItemClicked,
                    onLongClick = onItemLongClicked
                )
            }
        }

        when (items.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> item {
                LoadingItem()
            }
            is LoadState.Error -> item {
                ErrorItem(stringResource(R.string.something_went_wrong))
            }
        }
    }

    when (val state = items.loadState.refresh) {
        is LoadState.NotLoading -> Unit
        is LoadState.Loading -> Progress(modifier)
        is LoadState.Error -> onError.invoke(state.error)
    }
}