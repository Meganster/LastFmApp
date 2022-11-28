package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class ArtistDto(
    @SerializedName("image")
    override val image: List<Image>?,
    @SerializedName("mbid")
    val mbid: String = "",
    @SerializedName("listeners")
    val listeners: String = "",
    @SerializedName("streamable")
    val streamable: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = "",
    @Transient
    var artistQuery: String
) : ImageDto()