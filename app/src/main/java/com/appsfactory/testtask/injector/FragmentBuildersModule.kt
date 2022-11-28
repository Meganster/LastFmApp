package com.appsfactory.testtask.injector

import androidx.lifecycle.ViewModel
import com.appsfactory.testtask.ui.album.FavoriteAlbumsFragment
import com.appsfactory.testtask.ui.album.FavoriteAlbumsViewModel
import com.appsfactory.testtask.ui.search.SearchFragment
import com.appsfactory.testtask.ui.search.SearchViewModel
import com.appsfactory.testtask.ui.splash.SplashFragment
import com.appsfactory.testtask.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun screenSplash(): SplashFragment

    @ContributesAndroidInjector
    abstract fun screenFavoriteAlbums(): FavoriteAlbumsFragment

    @ContributesAndroidInjector
    abstract fun screenSearch(): SearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun provideSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteAlbumsViewModel::class)
    abstract fun provideFavoriteAlbumViewModel(viewModel: FavoriteAlbumsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun provideSearchViewModel(viewModel: SearchViewModel): ViewModel
}