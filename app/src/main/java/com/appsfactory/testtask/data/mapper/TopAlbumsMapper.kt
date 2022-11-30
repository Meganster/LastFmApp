package com.appsfactory.testtask.data.mapper

import com.appsfactory.testtask.data.dto.TopAlbumsResponse
import com.appsfactory.testtask.domain.model.Artist
import com.appsfactory.testtask.domain.model.TopAlbums
import com.appsfactory.testtask.utils.mapIgnoreErrors

object TopAlbumsMapper {

    fun mapToModel(response: TopAlbumsResponse, artist: Artist): TopAlbums = TopAlbums(
        albums = response.topAlbums?.album?.mapIgnoreErrors { AlbumMapper.mapToModel(it, artist) } ?: emptyList()
    )
}