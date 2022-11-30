package com.appsfactory.testtask.data.dto

abstract class ImageCollection {
    @Transient
    open val image: List<ImageDto>? = null

    fun findLastImage(): String? {
        return image?.findLast { !it.text.isNullOrBlank() }?.text
    }
}