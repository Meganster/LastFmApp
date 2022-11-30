package com.appsfactory.testtask.data.repository.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tracks",
    indices = [
        Index(value = ["name"], unique = true),
        Index(value = ["id"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = DetailsAlbumEntity::class,
            parentColumns = arrayOf("mid"),
            childColumns = arrayOf("mid"),
            onDelete = CASCADE
        )
    ]
)
data class TrackEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "mid")
    val mid: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "duration")
    val duration: Int
)