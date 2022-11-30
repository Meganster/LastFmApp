package com.appsfactory.testtask.domain.album.top

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.appsfactory.testtask.data.repository.LocalAlbumsRepository
import com.appsfactory.testtask.data.repository.RemoteAlbumsRepository
import com.appsfactory.testtask.domain.model.Album
import com.appsfactory.testtask.domain.model.Artist
import com.appsfactory.testtask.domain.model.DetailsAlbum
import javax.inject.Inject

class TopAlbumsInteractor @Inject constructor(
    private val remoteAlbumsRepository: RemoteAlbumsRepository,
    private val localAlbumsRepository: LocalAlbumsRepository
) {

    fun topAlbumsArtist(artist: Artist, pageSize: Int) = Pager(PagingConfig(pageSize)) {
        TopAlbumsDataSource(
            remoteAlbumsRepository = remoteAlbumsRepository,
            artist = artist
        )
    }.flow

    suspend fun loadDetailsAlbum(artist: Artist, album: Album): DetailsAlbum {
        return localAlbumsRepository.tryGetDetailsAlbum(album) ?: remoteAlbumsRepository.getDetailsAlbum(artist, album)
    }

    suspend fun isDetailsAlbumSavedLocally(album: Album): Boolean {
        return localAlbumsRepository.tryGetDetailsAlbum(album) != null
    }

    suspend fun saveArtistAndDetailsAlbum(artist: Artist, album: Album): DetailsAlbum {
        val detailsAlbum = remoteAlbumsRepository.getDetailsAlbum(artist, album)

        localAlbumsRepository.saveArtist(artist)
        localAlbumsRepository.saveDetailsAlbum(detailsAlbum)

        return detailsAlbum
    }

    suspend fun deleteDetailsAlbum(album: Album) {
        localAlbumsRepository.deleteDetailsAlbum(album.name)
    }
}