package com.appsfactory.testtask.data.repository.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "artists",
    indices = [
        Index(value = ["id"], unique = true),
        Index(value = ["name"], unique = true)
    ]
)
@Parcelize
data class ArtistEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "artist_icon")
    val icon: String?
) : Parcelable