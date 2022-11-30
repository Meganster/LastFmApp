package com.appsfactory.testtask.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.appsfactory.testtask.R
import com.appsfactory.testtask.data.dto.ArtistDto
import com.appsfactory.testtask.ui.common.ErrorItem
import com.appsfactory.testtask.ui.common.LoadingItem

@Composable
fun ArtistList(
    items: LazyPagingItems<ArtistDto>,
    onItemClicked: (ArtistDto) -> Unit,
    onError: (Throwable) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 72.dp)
    ) {
        items(items) { item ->
            item?.let { ArtistCard(it, onItemClicked) }
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
    }

    when (val state = items.loadState.refresh) {
        is LoadState.NotLoading -> Unit
        is LoadState.Loading -> Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Center
        ) {
            CircularProgressIndicator()
        }
        is LoadState.Error -> onError.invoke(state.error)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArtistCard(
    artistDto: ArtistDto,
    onClick: (ArtistDto) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 4.dp,
        onClick = { onClick.invoke(artistDto) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(42.dp)
                    .height(42.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(artistDto.findLastImage())
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_avatar_placeholder),
                contentDescription = stringResource(R.string.artist_image_description, artistDto.name),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(CenterVertically),
                text = artistDto.name,
                fontSize = 20.sp
            )
        }
    }
}