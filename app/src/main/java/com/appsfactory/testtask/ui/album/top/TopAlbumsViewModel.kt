package com.appsfactory.testtask.ui.album.top

import com.appsfactory.testtask.data.repository.AlbumsRepository
import com.appsfactory.testtask.ui.base.compose.BaseComposeViewModel
import javax.inject.Inject

class TopAlbumsViewModel @Inject constructor(
    private val albumsRepository: AlbumsRepository
) : BaseComposeViewModel() {

}