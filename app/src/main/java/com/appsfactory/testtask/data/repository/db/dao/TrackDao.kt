package com.appsfactory.testtask.data.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appsfactory.testtask.data.repository.db.entity.TrackEntity

@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<TrackEntity>)

    @Query("SELECT *  FROM tracks WHERE mid = :mid")
    suspend fun getTracksByMid(mid: Long): List<TrackEntity>?
}