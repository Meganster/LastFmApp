package com.appsfactory.testtask.domain.model

import android.os.Parcelable
import com.appsfactory.testtask.ui.common.BaseContentItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    val imageUrl: String?,
    val mbid: String?,
    val name: String,
    val artist: Artist
) : Parcelable, BaseContentItem {

    override fun getTitle(): String = name

    override fun getUrl(): String = imageUrl.orEmpty()
}