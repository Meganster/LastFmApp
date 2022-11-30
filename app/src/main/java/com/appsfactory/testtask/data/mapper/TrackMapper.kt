package com.appsfactory.testtask.data.mapper

import com.appsfactory.testtask.data.dto.TrackDto
import com.appsfactory.testtask.domain.model.Track

object TrackMapper {

    fun mapToModel(dto: TrackDto): Track = Track(
        name = requireNotNull(dto.name),
        duration = requireNotNull(dto.duration).toInt()
    )
}