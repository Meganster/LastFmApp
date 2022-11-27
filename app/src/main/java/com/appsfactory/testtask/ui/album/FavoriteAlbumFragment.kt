package com.appsfactory.testtask.ui.album

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class FavoriteAlbumFragment : Fragment() {

    override val classToken: Class<FavoriteAlbumViewModel>
        get() = FavoriteAlbumViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = AlbumController(this, isFavorite = true, context = context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSwipeToDelete()

        title.text = getString(R.string.favorites)

        search.setOnClickListener {
            navController().navigate(R.id.action_show_search_fragment)
        }
    }
}