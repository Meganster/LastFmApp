package com.appsfactory.testtask.data.mapper

import com.appsfactory.testtask.data.dto.AlbumDetailsResponse
import com.appsfactory.testtask.domain.model.Artist
import com.appsfactory.testtask.domain.model.DetailsAlbum
import com.appsfactory.testtask.utils.mapIgnoreErrors

object DetailsAlbumMapper {

    fun mapToModel(response: AlbumDetailsResponse, artist: Artist): DetailsAlbum = DetailsAlbum(
        imageUrl = ImageMapper.mapToModel(response.detailsAlbum.images),
        mbid = requireNotNull(response.detailsAlbum.mbid),
        artist = artist,
        name = requireNotNull(response.detailsAlbum.name),
        listeners = requireNotNull(response.detailsAlbum.listeners),
        tracks = response.detailsAlbum.tracks?.tracksDto?.mapIgnoreErrors { TrackMapper.mapToModel(it) } ?: emptyList()
    )
}