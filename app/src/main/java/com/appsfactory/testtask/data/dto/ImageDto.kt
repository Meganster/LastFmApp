package com.appsfactory.testtask.data.dto

// TODO rename
abstract class ImageDto {
    @Transient
    open val image: List<Image>? = null


    fun findLastImage(): String? {
        return image?.findLast { !it.text.isNullOrBlank() }?.text
    }
}