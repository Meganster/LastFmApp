package com.appsfactory.testtask.data.repository

import com.appsfactory.testtask.data.LastFmServiceApi
import com.appsfactory.testtask.data.mapper.DetailsAlbumMapper
import com.appsfactory.testtask.data.mapper.TopAlbumsMapper
import com.appsfactory.testtask.domain.model.Album
import com.appsfactory.testtask.domain.model.Artist
import com.appsfactory.testtask.domain.model.DetailsAlbum
import com.appsfactory.testtask.domain.model.TopAlbums
import javax.inject.Inject

class RemoteAlbumsRepository @Inject constructor(
    private val serviceApi: LastFmServiceApi
) {
    suspend fun getTopAlbums(artist: Artist, page: Int, itemsPerPage: Int): TopAlbums {
        return TopAlbumsMapper.mapToModel(
            serviceApi.getTopAlbums(artist = artist.name, page = page, limit = itemsPerPage), artist
        )
    }

    suspend fun getDetailsAlbum(artist: Artist, album: Album): DetailsAlbum {
        return DetailsAlbumMapper.mapToModel(
            serviceApi.getAlbumInfo(artist = artist.name, album = album.name), artist
        )
    }
}