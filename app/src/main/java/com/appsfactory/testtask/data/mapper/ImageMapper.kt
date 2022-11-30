package com.appsfactory.testtask.data.mapper

import com.appsfactory.testtask.data.dto.ImageDto

object ImageMapper {

    fun mapToModel(dto: List<ImageDto>?): String? = dto?.findLast { !it.text.isNullOrBlank() }?.text
}