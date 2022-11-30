package com.appsfactory.testtask.data.repository

import com.appsfactory.testtask.data.LastFmServiceApi
import com.appsfactory.testtask.data.mapper.SearchArtistMapper
import com.appsfactory.testtask.domain.model.SearchArtist
import javax.inject.Inject

class ArtistsRepository @Inject constructor(
    private val serviceApi: LastFmServiceApi
) {

    suspend fun searchArtist(artist: String, page: Int): SearchArtist {
        return SearchArtistMapper.mapToModel(serviceApi.searchArtist(artist, page))
    }
}