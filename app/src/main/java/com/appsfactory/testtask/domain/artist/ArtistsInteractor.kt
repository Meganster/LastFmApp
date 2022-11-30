package com.appsfactory.testtask.domain.artist

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.appsfactory.testtask.data.repository.ArtistsRepository
import javax.inject.Inject

class ArtistsInteractor @Inject constructor(
    private val artistsRepository: ArtistsRepository
) {

    fun searchArtist(artistName: String, pageSize: Int) = Pager(PagingConfig(pageSize)) {
        ArtistsDataSource(
            artistsRepository = artistsRepository,
            artistName = artistName
        )
    }.flow
}