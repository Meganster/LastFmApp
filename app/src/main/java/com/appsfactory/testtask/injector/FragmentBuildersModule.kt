package com.appsfactory.testtask.injector

import androidx.lifecycle.ViewModel
import com.appsfactory.testtask.ui.album.FavoriteAlbumsFragment
import com.appsfactory.testtask.ui.album.FavoriteAlbumsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun favoriteAlbumsFragment(): FavoriteAlbumsFragment

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteAlbumsViewModel::class)
    abstract fun provideFavoriteAlbumViewModel(viewModel: FavoriteAlbumsViewModel): ViewModel
}