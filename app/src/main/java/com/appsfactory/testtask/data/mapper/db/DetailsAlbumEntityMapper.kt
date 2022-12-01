package com.appsfactory.testtask.data.mapper.db

import com.appsfactory.testtask.db.entity.ArtistEntity
import com.appsfactory.testtask.db.entity.DetailsAlbumEntity
import com.appsfactory.testtask.db.entity.TrackEntity
import com.appsfactory.testtask.domain.model.DetailsAlbum

object DetailsAlbumEntityMapper {

    fun mapToEntity(details: DetailsAlbum): DetailsAlbumEntity = DetailsAlbumEntity(
        id = details.hashCode().toLong(),
        mid = details.generateId(),
        albumName = details.name,
        albumIcon = details.imageUrl,
        artistName = details.artist.name,
        listeners = details.listeners
    )

    fun mapToModel(entity: DetailsAlbumEntity, artistEntity: ArtistEntity, trackEntities: List<TrackEntity>): DetailsAlbum = DetailsAlbum(
        imageUrl = entity.albumIcon,
        mbid = entity.id.toString(),
        name = entity.albumName,
        listeners = entity.listeners,
        artist = ArtistEntityMapper.mapToModel(artistEntity),
        tracks = trackEntities.map { trackEntity -> TrackEntityMapper.mapToModel(trackEntity) }
    )
}