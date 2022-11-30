package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class DetailsAlbumDto(
    @SerializedName("image")
    val images: List<ImageDto>?,
    @SerializedName("mbid")
    val mbid: String?,
    @SerializedName("listeners")
    val listeners: String?,
    @SerializedName("playcount")
    val playcount: String?,
    @SerializedName("artist")
    val artist: String?,
    @SerializedName("wiki")
    val wiki: WikiDto?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("tracks")
    val tracks: TracksDto?
)
