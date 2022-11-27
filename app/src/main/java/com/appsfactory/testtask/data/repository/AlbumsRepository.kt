package com.appsfactory.testtask.data.repository

import com.appsfactory.testtask.data.LastFmServiceApi
import com.appsfactory.testtask.data.dto.FavoriteAlbumsResponse
import com.appsfactory.testtask.data.dto.SearchArtistResponse
import com.appsfactory.testtask.domain.model.Album
import javax.inject.Inject

class AlbumsRepository @Inject constructor(
    private val serviceApi: LastFmServiceApi
) {

    suspend fun getFavoriteAlbums(page: Int, itemsPerPage: Int): FavoriteAlbumsResponse {
        return FavoriteAlbumsResponse(
            page = page,
            albums = listOf(
                Album("Belal", "1", "lmlm", "null", "Mr."),
                Album("dsvv", "5", "45frer", "null", "Mr."),
                Album("d", "4", "edverv4", "null", "Mr."),
                Album("wefwef", "3", "wef21", "null", "Mr."),
                Album("sxcms", "2", "123", "null", "Mrs.")
            )
        )
    }

    suspend fun searchArtist(page: Int): SearchArtistResponse {
        return serviceApi.searchArtist("bones", page)
    }
}