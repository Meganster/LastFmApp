package com.appsfactory.testtask.data.dto

interface ApiError {
    // @SerializedName("error")
    val error: Int?

    //@SerializedName("message")
    val message: String?
}