package com.appsfactory.testtask.data.mapper

import com.appsfactory.testtask.data.dto.SearchArtistResponse
import com.appsfactory.testtask.domain.model.SearchArtist
import com.appsfactory.testtask.utils.mapIgnoreErrors

object SearchArtistMapper {

    fun mapToModel(response: SearchArtistResponse): SearchArtist = SearchArtist(
        artists = response.searchResult?.artistMatches?.artist?.mapIgnoreErrors { ArtistMapper.mapToModel(it) } ?: emptyList()
    )
}