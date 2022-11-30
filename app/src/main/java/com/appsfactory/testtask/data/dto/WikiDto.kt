package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class WikiDto(
    @SerializedName("summary")
    val summary: String?,
    @SerializedName("published")
    val published: String?,
    @SerializedName("content")
    val content: String?
)