package com.appsfactory.testtask.data

import com.appsfactory.testtask.data.dto.SearchArtistResponse
import com.appsfactory.testtask.data.dto.TopAlbumResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmServiceApi {

    @GET("?method=artist.search")
    suspend fun searchArtist(
        @Query("artist") artistQuery: String? = "",
        @Query("page") page: Int = 1
    ): SearchArtistResponse

    @GET("?method=artist.gettopalbums")
    suspend fun getTopAlbums(
        @Query("artist") artist: String? = "",
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = DEFAULT_PAGE_SIZE,
        @Query("autocorrect") autocorrect: Int = 1
    ): TopAlbumResponse

    //    @GET("?method=album.getinfo")
    //    fun getAlbumInfo(
    //        @Query("artist") artist: String? = "", @Query("album") album: String? = "", @Query(
    //            "autocorrect"
    //        ) autocorrect: Int = 1
    //    ): Flowable<AlbumDetailsResponse>

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
    }
}
