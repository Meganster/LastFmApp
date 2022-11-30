package com.appsfactory.testtask.domain.model

import com.appsfactory.testtask.ui.common.BaseContentItem
import java.io.Serializable

data class DetailsAlbum(
    val imageUrl: String?,
    val mbid: String,
    val name: String,
    val artist: Artist,
    val listeners: String,
    val tracks: List<Track>
) : Serializable, BaseContentItem {

    fun generateId(): Long {
        return (name + artist).hashCode().toLong()
    }

    override fun getTitle(): String = name

    override fun getUrl(): String = imageUrl.orEmpty()
}