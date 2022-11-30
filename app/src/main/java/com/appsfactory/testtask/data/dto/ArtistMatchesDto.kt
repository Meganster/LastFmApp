package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class ArtistMatchesDto(
    @SerializedName("artist")
    val artist: List<ArtistDto>?
)