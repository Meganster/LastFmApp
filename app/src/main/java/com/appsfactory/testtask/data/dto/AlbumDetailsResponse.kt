package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class AlbumDetailsResponse(
    @SerializedName("album")
    val detailsAlbum: DetailsAlbumDto,
    @SerializedName("error")
    override val error: Int,
    @SerializedName("message")
    override val message: String
) : ApiError
