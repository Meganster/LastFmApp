package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("image")
    val image: List<ImageDto>?,
    @SerializedName("mbid")
    val mbid: String?,
    @SerializedName("artist")
    val artist: ArtistDto?,
    @SerializedName("name")
    val name: String?
)