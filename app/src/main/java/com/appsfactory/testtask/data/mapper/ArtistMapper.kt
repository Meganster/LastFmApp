package com.appsfactory.testtask.data.mapper

import com.appsfactory.testtask.data.dto.ArtistDto
import com.appsfactory.testtask.domain.model.Artist

object ArtistMapper {

    fun mapToModel(dto: ArtistDto): Artist = Artist(
        imageUrl = ImageMapper.mapToModel(dto.image),
        mbid = requireNotNull(dto.mbid),
        name = requireNotNull(dto.name)
    )
}