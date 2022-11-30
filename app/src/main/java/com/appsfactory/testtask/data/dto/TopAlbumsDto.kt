package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class TopAlbumsDto(
    @SerializedName("album")
    val album: List<AlbumDto>?,
    @SerializedName("@attr")
    val attr: AttrDto?
)