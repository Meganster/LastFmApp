package com.appsfactory.testtask.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appsfactory.testtask.db.entity.ArtistEntity

@Dao
interface ArtistDao {

    @Query("DELETE FROM artists")
    suspend fun deleteAll()

    @Query("DELETE FROM artists WHERE name = :artistName")
    suspend fun deleteAllForArtist(artistName: String)

    @Query("SELECT * FROM artists WHERE name = :artistName")
    suspend fun getArtistsByName(artistName: String): ArtistEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ArtistEntity)
}
