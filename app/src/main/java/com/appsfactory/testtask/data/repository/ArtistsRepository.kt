package com.appsfactory.testtask.data.repository

import com.appsfactory.testtask.data.LastFmServiceApi
import com.appsfactory.testtask.data.dto.SearchArtistResponse
import javax.inject.Inject

class ArtistsRepository @Inject constructor(
    private val serviceApi: LastFmServiceApi
) {

    suspend fun searchArtist(query: String, page: Int): SearchArtistResponse {
        return serviceApi.searchArtist(query, page)
    }
}