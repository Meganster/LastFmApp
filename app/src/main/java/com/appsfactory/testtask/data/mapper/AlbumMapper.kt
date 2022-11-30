package com.appsfactory.testtask.data.mapper

import com.appsfactory.testtask.data.dto.AlbumDto
import com.appsfactory.testtask.domain.model.Album
import com.appsfactory.testtask.domain.model.Artist

object AlbumMapper {

    fun mapToModel(dto: AlbumDto, artist: Artist): Album = Album(
        imageUrl = ImageMapper.mapToModel(dto.image),
        artist = artist,
        mbid = dto.mbid,
        name = requireNotNull(dto.name)
    )
}