package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageDto(
    @SerializedName("#text") val text: String?,
    @SerializedName("size") val size: String
) : Serializable