package com.appsfactory.testtask.data.dto

import com.appsfactory.testtask.domain.model.Album
import com.google.gson.annotations.SerializedName

data class TopAlbums(
    @SerializedName("album") val album: List<Album>,
    @SerializedName("@attr") val attr: Attr
)