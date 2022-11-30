package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class TopAlbumsResponse(
    @SerializedName("topalbums")
    val topAlbums: TopAlbumsDto?,
    @SerializedName("error")
    override val error: Int?,
    @SerializedName("message")
    override val message: String?
) : ApiError
