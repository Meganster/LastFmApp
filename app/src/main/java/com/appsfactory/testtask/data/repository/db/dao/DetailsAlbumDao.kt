package com.appsfactory.testtask.data.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appsfactory.testtask.data.repository.db.entity.DetailsAlbumEntity

@Dao
interface DetailsAlbumDao {

    @Query("DELETE FROM details_album WHERE album_name = :albumName")
    suspend fun deleteDetailsAlbum(albumName: String)

    @Query("SELECT *  FROM details_album WHERE album_name = :albumName")
    suspend fun getDetailsAlbum(albumName: String): DetailsAlbumEntity?

    @Query("SELECT *  FROM details_album")
    suspend fun getAll(): List<DetailsAlbumEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DetailsAlbumEntity)
}