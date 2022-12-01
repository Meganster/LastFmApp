package com.appsfactory.testtask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appsfactory.testtask.db.dao.ArtistDao
import com.appsfactory.testtask.db.dao.DetailsAlbumDao
import com.appsfactory.testtask.db.dao.DetailsAlbumWithTracksDao
import com.appsfactory.testtask.db.dao.TrackDao
import com.appsfactory.testtask.db.entity.ArtistEntity
import com.appsfactory.testtask.db.entity.DetailsAlbumEntity
import com.appsfactory.testtask.db.entity.TrackEntity

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
