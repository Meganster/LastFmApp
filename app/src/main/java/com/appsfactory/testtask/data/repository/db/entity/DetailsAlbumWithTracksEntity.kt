package com.appsfactory.testtask.data.repository.db.entity

import androidx.room.Embedded
import androidx.room.Relation
import java.util.Objects

class DetailsAlbumWithTracksEntity {

    @Embedded
    var detailsAlbum: DetailsAlbumEntity? = null

    @Relation(parentColumn = "mid", entityColumn = "mid")
    var relations: List<TrackEntity>? = null

    override fun equals(other: Any?): Boolean = when {
        other === this -> true
        other is DetailsAlbumWithTracksEntity -> detailsAlbum == other.detailsAlbum && relations == other.relations
        else -> false
    }

    override fun hashCode(): Int = Objects.hash(detailsAlbum, relations)
}
