package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArtistDto(
    @SerializedName("image")
    override val image: List<ImageDto>?,
    @SerializedName("mbid")
    val mbid: String = "",
    @SerializedName("listeners")
    val listeners: String = "",
    @SerializedName("streamable")
    val streamable: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = ""
) : ImageCollection(), Serializable