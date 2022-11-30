package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class AttrDto(
    @SerializedName("artist") val artist: String?,
    @SerializedName("page") val page: String?,
    @SerializedName("perPage") val perPage: String?,
    @SerializedName("totalPages") val totalPages: String?,
    @SerializedName("total") val total: String?,
    @SerializedName("rank") val rank: String?,
    @SerializedName("for") val forArtist: String?
)