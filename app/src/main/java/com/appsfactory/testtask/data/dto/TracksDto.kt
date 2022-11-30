package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class TracksDto(
    @SerializedName("track")
    val tracksDto: List<TrackDto>?
)