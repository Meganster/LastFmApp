package com.appsfactory.testtask.data.dto

data class FavoriteAlbumsResponse(
    val albums: List<AlbumDto>,
    val page: Int
)