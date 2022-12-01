package com.appsfactory.testtask.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "details_album",
    indices = [
        Index(value = ["album_name"], unique = true),
        Index(value = ["mid"], unique = true),
        Index(value = ["id"], unique = true),
        Index(value = ["artist_name"])
    ]
)
data class DetailsAlbumEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "mid")
    val mid: Long = 0,
    @ColumnInfo(name = "album_name")
    val albumName: String,
    @ColumnInfo(name = "album_icon")
    val albumIcon: String?,
    @ColumnInfo(name = "artist_name")
    val artistName: String,
    @ColumnInfo(name = "listeners")
    val listeners: String
)