package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class TrackDto(
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("@attr")
    val attr: AttrDto,
    @SerializedName("artist")
    val artist: ArtistDto?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)