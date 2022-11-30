package com.appsfactory.testtask.data.repository.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.appsfactory.testtask.data.repository.db.entity.DetailsAlbumWithTracksEntity

@Dao
interface DetailsAlbumWithTracksDao {

    @Transaction
    @Query("SELECT * FROM details_album WHERE album_name = :albumName")
    suspend fun getAllTracksForAlbum(albumName: String): DetailsAlbumWithTracksEntity?
}