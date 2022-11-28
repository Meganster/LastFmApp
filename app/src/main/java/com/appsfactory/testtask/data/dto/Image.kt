package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

// TODO rename
data class Image(
    @SerializedName("#text") val text: String?,
    @SerializedName("size") val size: String
)