package com.appsfactory.testtask.data.mapper.db

import com.appsfactory.testtask.db.entity.TrackEntity
import com.appsfactory.testtask.domain.model.DetailsAlbum
import com.appsfactory.testtask.domain.model.Track

object TrackEntityMapper {

    fun mapToEntity(detailsAlbum: DetailsAlbum, track: Track): TrackEntity = TrackEntity(
        id = track.hashCode().toLong(),
        mid = detailsAlbum.generateId(),
        name = track.name,
        duration = track.duration
    )

    fun mapToModel(entity: TrackEntity): Track = Track(
        duration = entity.duration,
        name = entity.name
    )
}