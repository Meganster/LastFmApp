package com.appsfactory.testtask.data.mapper.db

import com.appsfactory.testtask.data.repository.db.entity.ArtistEntity
import com.appsfactory.testtask.domain.model.Artist

object ArtistEntityMapper {

    fun mapToEntity(artist: Artist): ArtistEntity = ArtistEntity(
        id = artist.hashCode().toLong(),
        icon = artist.imageUrl,
        name = artist.name
    )

    fun mapToModel(entity: ArtistEntity): Artist = Artist(
        mbid = entity.id.toString(),
        imageUrl = entity.icon,
        name = entity.name
    )
}