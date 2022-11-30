package com.appsfactory.testtask.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.appsfactory.testtask.R.drawable
import com.appsfactory.testtask.R.string

interface BaseContentItem {
    fun getTitle(): String

    fun getUrl(): String
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T : BaseContentItem> ContentItem(
    modifier: Modifier,
    item: T,
    onClick: (T) -> Unit,
    onLongClick: (T) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .combinedClickable(
                onClick = { onClick.invoke(item) },
                onLongClick = { onLongClick.invoke(item) }
            ),
        elevation = 4.dp
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
                model = Builder(LocalContext.current)
                    .data(item.getUrl())
                    .crossfade(true)
                    .build(),
                error = painterResource(drawable.ic_avatar_placeholder),
                placeholder = painterResource(drawable.ic_avatar_placeholder),
                contentDescription = stringResource(string.artist_image_description, item.getTitle()),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterVertically),
                text = item.getTitle(),
                fontSize = 16.sp
            )
        }
    }
}