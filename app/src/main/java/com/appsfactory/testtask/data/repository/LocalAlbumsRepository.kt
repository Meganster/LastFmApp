package com.appsfactory.testtask.data.repository

import androidx.room.withTransaction
import com.appsfactory.testtask.data.mapper.db.ArtistEntityMapper
import com.appsfactory.testtask.data.mapper.db.DetailsAlbumEntityMapper
import com.appsfactory.testtask.data.mapper.db.TrackEntityMapper
import com.appsfactory.testtask.db.TestTaskDb
import com.appsfactory.testtask.db.dao.ArtistDao
import com.appsfactory.testtask.db.dao.DetailsAlbumDao
import com.appsfactory.testtask.db.dao.DetailsAlbumWithTracksDao
import com.appsfactory.testtask.db.dao.TrackDao
import com.appsfactory.testtask.domain.model.Album
import com.appsfactory.testtask.domain.model.Artist
import com.appsfactory.testtask.domain.model.DetailsAlbum
import timber.log.Timber
import javax.inject.Inject

class LocalAlbumsRepository @Inject constructor(
    private val db: TestTaskDb
) {
    private val artistDao: ArtistDao = db.artistDao()
    private val detailsAlbumDao: DetailsAlbumDao = db.detailsAlbumDao()
    private val detailsAlbumWithTracksDao: DetailsAlbumWithTracksDao = db.detailsAlbumWithTracksDao()
    private val trackDao: TrackDao = db.trackDao()

    suspend fun getFavoriteAlbums(page: Int, itemsPerPage: Int): List<DetailsAlbum> {
        val offset = if (page <= 1) {
            0
        } else {
            (page - 1) * itemsPerPage
        }

        return detailsAlbumDao.getByPage(limit = itemsPerPage, offset = offset).orEmpty().map {
            val artistEntity = artistDao.getArtistsByName(it.artistName)!!
            val trackEntities = trackDao.getTracksByMid(it.mid).orEmpty()
            DetailsAlbumEntityMapper.mapToModel(it, artistEntity, trackEntities)
        }
    }

    suspend fun tryGetDetailsAlbum(album: Album): DetailsAlbum? {
        return try {
            val result = detailsAlbumWithTracksDao.getAllTracksForAlbum(albumName = album.name)

            result?.detailsAlbum?.let {
                val artistEntity = artistDao.getArtistsByName(it.artistName)!!
                val trackEntities = result.relations.orEmpty()
                DetailsAlbumEntityMapper.mapToModel(it, artistEntity, trackEntities)
            }
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

    suspend fun saveArtist(artist: Artist) {
        db.withTransaction {
            artistDao.insert(ArtistEntityMapper.mapToEntity(artist))
        }
    }

    suspend fun saveDetailsAlbum(detailsAlbum: DetailsAlbum) {
        db.withTransaction {
            detailsAlbumDao.insert(DetailsAlbumEntityMapper.mapToEntity(detailsAlbum))
            trackDao.insertAll(detailsAlbum.tracks.map {
                TrackEntityMapper.mapToEntity(detailsAlbum, it)
            })
        }
    }

    suspend fun deleteDetailsAlbum(albumName: String) {
        db.withTransaction {
            detailsAlbumDao.deleteDetailsAlbum(albumName)
        }
    }
}