package com.appsfactory.testtask.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appsfactory.testtask.db.entity.DetailsAlbumEntity

@Dao
interface DetailsAlbumDao {

    @Query("DELETE FROM details_album WHERE album_name = :albumName")
    suspend fun deleteDetailsAlbum(albumName: String)

    @Query("SELECT *  FROM details_album WHERE album_name = :albumName")
    suspend fun getDetailsAlbum(albumName: String): DetailsAlbumEntity?

    @Query("SELECT *  FROM details_album")
    suspend fun getAll(): List<DetailsAlbumEntity>?

    @Query("SELECT *  FROM details_album LIMIT :limit OFFSET :offset")
    suspend fun getByPage(limit: Int, offset: Int): List<DetailsAlbumEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: DetailsAlbumEntity)
}