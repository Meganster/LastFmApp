package com.appsfactory.testtask.data

import com.appsfactory.testtask.data.dto.SearchArtistResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmServiceApi {

    @GET("?method=artist.search")
    suspend fun searchArtist(@Query("artist") artistQuery: String? = "", @Query("page") page: Int = 1): SearchArtistResponse

    //    @GET("?method=artist.gettopalbums")
    //    fun getTopAlbums(
    //        @Query("artist") artist: String? = "", @Query("page") page: Int = 1, @Query("limit") limit: Int = PAGE_SIZE, @Query(
    //            "autocorrect"
    //        ) autocorrect: Int = 1
    //    ): Flowable<TopAlbumResponse>
    //
    //    @GET("?method=album.getinfo")
    //    fun getAlbumInfo(
    //        @Query("artist") artist: String? = "", @Query("album") album: String? = "", @Query(
    //            "autocorrect"
    //        ) autocorrect: Int = 1
    //    ): Flowable<AlbumDetailsResponse>
}
