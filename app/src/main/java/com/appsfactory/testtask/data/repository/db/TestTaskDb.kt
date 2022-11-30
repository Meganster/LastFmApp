package com.appsfactory.testtask.data.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appsfactory.testtask.data.repository.db.dao.ArtistDao
import com.appsfactory.testtask.data.repository.db.dao.DetailsAlbumDao
import com.appsfactory.testtask.data.repository.db.dao.DetailsAlbumWithTracksDao
import com.appsfactory.testtask.data.repository.db.dao.TrackDao
import com.appsfactory.testtask.data.repository.db.entity.ArtistEntity
import com.appsfactory.testtask.data.repository.db.entity.DetailsAlbumEntity
import com.appsfactory.testtask.data.repository.db.entity.TrackEntity

@Database(
    entities = [
        ArtistEntity::class,
        DetailsAlbumEntity::class,
        TrackEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TestTaskDb : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
    abstract fun detailsAlbumDao(): DetailsAlbumDao
    abstract fun trackDao(): TrackDao
    abstract fun detailsAlbumWithTracksDao(): DetailsAlbumWithTracksDao
}
