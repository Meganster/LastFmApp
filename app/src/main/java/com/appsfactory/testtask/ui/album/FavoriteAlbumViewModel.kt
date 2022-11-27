package com.appsfactory.testtask.ui.album

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import dadoufi.musicmanager.base.PagedViewModel
import dadoufi.musicmanager.data.entities.AlbumEntity
import dadoufi.musicmanager.data.repositories.favorite.FavoriteRepository
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class FavoriteAlbumViewModel @Inject constructor(
    override var repository: FavoriteRepository
) : PagedViewModel<Any, AlbumEntity>(schedulers, repository) {

    var observer: Observer<PagedList<AlbumEntity>?>? = null

    fun removeFavoriteAlbum(mid: Long) {
        disposables += repository.removeFavorite(mid)
            .subscribe()
    }
}