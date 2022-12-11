package com.appsfactory.testtask.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
    val duration: Int,
    val name: String
) : Parcelable