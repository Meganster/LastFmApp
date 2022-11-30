package com.appsfactory.testtask.data

import com.appsfactory.testtask.data.dto.AlbumDetailsResponse
import com.appsfactory.testtask.data.dto.SearchArtistResponse
import com.appsfactory.testtask.data.dto.TopAlbumsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmServiceApi {

    @GET("?method=artist.search")
    suspend fun searchArtist(
        @Query("artist") artist: String?,
        @Query("page") page: Int
    ): SearchArtistResponse

    @GET("?method=artist.gettopalbums")
    suspend fun getTopAlbums(
        @Query("artist") artist: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = DEFAULT_PAGE_SIZE
    ): TopAlbumsResponse

    @GET("?method=album.getinfo")
    suspend fun getAlbumInfo(
        @Query("artist") artist: String,
        @Query("album") album: String
    ): AlbumDetailsResponse

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
    }
}
