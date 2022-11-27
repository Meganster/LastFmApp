package com.appsfactory.testtask.data.dto

import com.appsfactory.testtask.domain.model.Album

data class FavoriteAlbumsResponse(
    val albums: List<Album>,
    val page: Int
)