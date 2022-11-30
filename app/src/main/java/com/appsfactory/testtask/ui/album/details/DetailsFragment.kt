package com.appsfactory.testtask.ui.album.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.appsfactory.testtask.R
import com.appsfactory.testtask.ui.base.compose.BaseComposeFragment
import com.appsfactory.testtask.ui.common.Snackbar
import com.appsfactory.testtask.ui.common.Toolbar

class DetailsFragment : BaseComposeFragment<DetailsViewModel>() {

    override val classType = DetailsViewModel::class.java

    private lateinit var params: DetailsFragmentArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        params = DetailsFragmentArgs.fromBundle(requireArguments())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return ComposeView(requireContext()).apply {
            setContent {
                val snackbarHostState = remember { SnackbarHostState() }
                val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)

                initSnackbarObserver(snackbarHostState)

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    scaffoldState = scaffoldState,
                    topBar = { Toolbar(stringResource(R.string.detail_album_title, params.detailsAlbum.name)) },
                    snackbarHost = { Snackbar(it) }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .width(72.dp)
                                        .height(72.dp)
                                        .align(Alignment.CenterVertically),
                                    model = Builder(LocalContext.current)
                                        .data(params.detailsAlbum.imageUrl)
                                        .crossfade(true)
                                        .build(),
                                    error = painterResource(R.drawable.ic_avatar_placeholder),
                                    placeholder = painterResource(R.drawable.ic_avatar_placeholder),
                                    contentDescription = stringResource(R.string.artist_image_description, params.detailsAlbum.name),
                                    contentScale = ContentScale.Crop
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        text = params.detailsAlbum.name,
                                        fontSize = 20.sp
                                    )
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 8.dp),
                                        text = stringResource(R.string.detail_listeners, params.detailsAlbum.listeners),
                                        fontSize = 16.sp
                                    )
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 8.dp),
                                        text = stringResource(R.string.detail_artist, params.detailsAlbum.artist.name),
                                        fontSize = 16.sp
                                    )
                                }
                            }

                            if (params.detailsAlbum.tracks.isNotEmpty()) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .padding(top = 16.dp),
                                    text = stringResource(R.string.detail_tracks),
                                    fontSize = 20.sp
                                )
                            }
                        }

                        items(params.detailsAlbum.tracks) { track ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp, horizontal = 16.dp)
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .weight(1f),
                                    text = track.name,
                                    fontSize = 16.sp
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp),
                                    color = Color.Gray,
                                    text = track.duration.formatAsTimeString(),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun Int.formatAsTimeString(): String {
        val minutes: Int = this / SECONDS_IN_MINUTE
        val seconds: Int = this % SECONDS_IN_MINUTE

        return "$minutes:$seconds"
    }

    companion object {
        private const val SECONDS_IN_MINUTE = 60
    }
}