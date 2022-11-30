package com.appsfactory.testtask.domain.model

import com.appsfactory.testtask.ui.common.BaseContentItem
import java.io.Serializable

data class Album(
    val imageUrl: String?,
    val mbid: String?,
    val name: String,
    val artist: Artist
) : Serializable, BaseContentItem {

    override fun getTitle(): String = name

    override fun getUrl(): String = imageUrl.orEmpty()
}